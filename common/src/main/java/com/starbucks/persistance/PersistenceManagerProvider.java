package com.starbucks.persistance;

import com.codahale.metrics.MetricRegistry;
import org.datanucleus.api.jdo.JDOPersistenceManager;
import org.datanucleus.management.ManagerStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.time.Instant;

/**
 * This class is a wrapper for JDO's PersistenceManager to implement AutoCloseable
 * JDO/DN is supposed to have this in the future, it's in their git repo, but not in the packages
 */
public class PersistenceManagerProvider implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceManagerProvider.class);

    private PersistenceManager persistenceManager;
    private Transaction tx; // probably not necessary but just in case PM changes its internal Transaction
    private int txDepth;
    private long txnStartTime;
    private MetricRegistry metricRegistry;
    private static long TXN_DURATION_THRESHOLD = 5; // log all txns that take longer than 5 seconds

    public PersistenceManagerProvider(final PersistenceManager persistenceManager,
                                      final MetricRegistry metricRegistry) {
        this.persistenceManager = persistenceManager;
        this.metricRegistry = metricRegistry;
        this.txDepth = 0;
    }

    public PersistenceManager get() {
        return persistenceManager;
    }

    /**
     * Starts a new transaction using this instance's PersistenceManager.
     *
     * <p>The returned Transaction has already executed {@code begin()}. Do not call {@code begin()} again.
     *
     * <p>Clients <b>must</b> call {@code commit()} on the Transaction before this PersistenceManageProvider is closed.
     * Failure to do so will result in {@code rollback()} being called on the Transaction. This is to ensure
     * that transactions are not committed when an uncaught exception occurs.
     *
     * <p>Example usage:
     * <pre><code>
     * final PersistenceManager pm;
     * try (final PersistenceManagerProvider pmp = new PersistenceManagerProvider(pm)) {
     * final Transaction tx = pmp.startTransaction();
     * // pm.makePersistent(...);
     * tx.commit();
     * }
     * </code></pre>
     *
     * @throws IllegalStateException If a transaction has already been started.
     */
    public void startTransaction() {
        if (tx != null && tx.isActive()) {
            txDepth++;
            return;
        }
        tx = persistenceManager.currentTransaction();
        tx.begin();
        persistenceManager.newQuery(Query.SQL, "BEGIN;").execute();
        txDepth = 1;
        // start a timer
        txnStartTime = Instant.now().getEpochSecond();
    }

    public void commitTransaction() {
        if (txDepth == 1) {
            tx.commit();
            final long duration = Instant.now().getEpochSecond() - txnStartTime;
            if (duration > TXN_DURATION_THRESHOLD) {
                final String stackTrace = new Throwable().getStackTrace()[1].toString(); // element 0 is this method
                LOGGER.warn("[TXN-DURATION] Found longer than {} seconds, duration={}, method={}", TXN_DURATION_THRESHOLD, duration, stackTrace);
            }
        } else if (txDepth > 1) {
            txDepth--;
        } else {
            throw new IllegalStateException("Attempting to commit a transaction that has not begun.");
        }
    }

    public void rollbackTransactionIfActive() {
        if (getTxDepth() > 1) {
            commitTransaction(); // decrementing the dx depth
        } else {
            rollbackTransaction(); // rolling back since a leaked txn is active
        }
    }

    public void rollbackTransaction() {
        if (tx != null && tx.isActive()) {
            final String stackTrace = new Throwable().getStackTrace()[1].toString(); // element 0 is this method
            LOGGER.error("ROLLING BACK UNCOMMITTED TRANSACTION FROM " + stackTrace);
            tx.rollback(); // never throws if isActive() is true
        } else {
            LOGGER.error("NOTHING TO ROLLBACK");
        }
        txDepth = 0;
    }

    public int getTxDepth() {
        return txDepth;
    }

    public Transaction getTx() {
        return tx;
    }

    @Override
    public void close() {
        try {
            if (tx != null && tx.isActive()) {
                final String stackTrace = new Throwable().getStackTrace()[1].toString(); // element 0 is this method
                LOGGER.error("ROLLING BACK UNCOMMITTED TRANSACTION FROM " + stackTrace);
                tx.rollback(); // never throws if isActive() is true
            }
            JDOPersistenceManager dnpm = (JDOPersistenceManager) persistenceManager;
            ManagerStatistics stats = dnpm.getExecutionContext().getStatistics();
            if (stats != null) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("getNumberOfDatastoreReads: " + stats.getNumberOfDatastoreReads()
                            + " getNumberOfDatastoreWrites: " + stats.getNumberOfDatastoreWrites()
                            + " getQueryErrorTotalCount: " + stats.getQueryErrorTotalCount()
                            + " getTransactionExecutionTimeAverage: " + stats.getTransactionExecutionTimeAverage()
                            + " getTransactionExecutionTotalTime: " + stats.getTransactionExecutionTotalTime()
                            + " getQueryExecutionTimeAverage: " + stats.getQueryExecutionTimeAverage()
                            + " getQueryExecutionTotalTime: " + stats.getQueryExecutionTotalTime()
                    );
                }
            } else {
                LOGGER.debug("null jdo stats");
            }
        } finally {
            persistenceManager.close();
        }
    }
}
