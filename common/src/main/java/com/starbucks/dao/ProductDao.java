package com.starbucks.dao;

import com.starbucks.model.Product;
import com.starbucks.persistance.DBConn;
import com.starbucks.view.ProductView;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class ProductDao  extends BaseDao {

    private DBConn conn;

    @Inject
    public ProductDao(final DBConn conn) {
        this.conn = conn;
    }

    public static final String PRODUCT_BASE_QUERY = "SELECT p.id as id, " +
            "p.type as type, " +
            "p.name as name, " +
            "p.cost as cost, " +
            "p.totalQuantity as totalQuantity, " +
            "p.isActive as isActive, " +
            "p.created as created, " +
            "p.updated as updated " +
            "FROM `Product` p ";

    public static final String GET_PRODUCT_BY_ID = PRODUCT_BASE_QUERY + "WHERE p.id = :productId AND isActive = 1";

    public static final String DELETE_PRODUCT_BY_ID = "UPDATE `Product` p SET p.isActive = 0 WHERE id = :productId";

    public static final String UPDATE_PRODUCT_BY_ID = "UPDATE `Product` p SET p.totalQuantity = :totalQuantity WHERE p.id = :productId AND isActive = 1";


    public Optional<Product> fetchProductById(final int productId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("productId", String.valueOf(productId));

        Product productRecord = null;

        Query query = pm.newQuery(SQL, GET_PRODUCT_BY_ID);
        query.setResultClass(Product.class);
        query.setUnique(true);
        productRecord  = (Product) query.executeWithMap(parameters);

        return Optional.ofNullable(productRecord);
    }

    public ProductView createProduct(final Product product) {
        PersistenceManager pm = conn.getPmp();
        Transaction tx = pm.currentTransaction();
        Product productRecord = null;
        try {
            tx.begin();
            productRecord = (Product) persistAndFetch(pm, product);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return new ProductView(productRecord);
    }

    public boolean updateProduct(final int productId, final int totalQuantity) {
        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("productId", String.valueOf(productId));
        parameters.put("totalQuantity", String.valueOf(totalQuantity));
        Transaction tx = pm.currentTransaction();
        Long recordCount = 0L;
        try {
            tx.begin();
            Query query = pm.newQuery(SQL, UPDATE_PRODUCT_BY_ID);
            query.setResultClass(Long.class);
            recordCount = (Long) query.executeWithMap(parameters);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return recordCount == 1;
    }


    public boolean deleteProductById(final int productId) {

        PersistenceManager pm = conn.getPmp();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("productId", String.valueOf(productId));
        Transaction tx = pm.currentTransaction();
        Long recordCount = 0L;
        try {
            tx.begin();
            Query query = pm.newQuery(SQL, DELETE_PRODUCT_BY_ID);
            query.setResultClass(Long.class);
            recordCount = (Long) query.executeWithMap(parameters);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return recordCount == 1;
    }

}
