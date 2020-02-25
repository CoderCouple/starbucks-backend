package com.starbucks.persistance;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;


public class PersistenceDirector {

    public static final String JDO_READ_ONLY_OPTION_NAME = "javax.jdo.option.ReadOnly";
    private final PersistenceManagerFactory pmf;
    private final MetricRegistry metricRegistry;
    private boolean isUnitTest;

    @Inject
    public PersistenceDirector(final BaseJDOConfig config,
                               final MetricRegistry metricRegistry ,
                               final boolean isUnitTest) {
        this.pmf = JDOHelper.getPersistenceManagerFactory(config);
        this.metricRegistry = metricRegistry;
        this.isUnitTest = isUnitTest;
    }

    /**
     * Gets PersistenceManagerProvider
     *
     * @return a new PersistenceManagerProvider
     */
    public PersistenceManagerProvider getPmp() {
        return pmpFactory(false, metricRegistry);
    }

    /**
     * Gets PersistenceManagerProvider
     *
     * @return a new PersistenceManagerProvider
     */
    public PersistenceManagerProvider getReadOnlyPmp() {
        return pmpFactory(true, metricRegistry);
    }

    /**
     * Gets PersistenceManagerFactory.
     *
     * @return Value of pmf.
     */
    public PersistenceManagerFactory getPmf() {
        return pmf;
    }

    public PersistenceManagerProvider pmpFactory(final boolean readOnly, final MetricRegistry metricRegistry) {
        final PersistenceManager pm = pmf.getPersistenceManager();
        if (this.isUnitTest && readOnly) {
            pm.setProperty(JDO_READ_ONLY_OPTION_NAME, true);
        }
        return new PersistenceManagerProvider(pm, metricRegistry);
    }
}
