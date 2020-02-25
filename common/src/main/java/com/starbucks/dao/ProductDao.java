package com.starbucks.dao;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.starbucks.model.Product;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.persistance.PersistentDao;
import com.starbucks.view.ProductView;

import javax.jdo.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class ProductDao extends PersistentDao<Product> {

    @AssistedInject
    public ProductDao(final @Assisted PersistenceManagerProvider pmp) {
        super(pmp, Product.class);
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

    public static final String UPDATE_PRODUCT_BY_ID = "UPDATE `Product` AS `dest`, " +
                                                      "(   SELECT totalQuantity " +
                                                      "        FROM `Product` " +
                                                      "        WHERE `type` = :type " +
                                                      "    ) AS `src` " +
                                                      "SET `dest`.`totalQuantity` = `src`.`totalQuantity` - :quantity " +
                                                      "WHERE `dest`.`type` = :type " +
                                                      "AND isActive = 1";


    public Optional<Product> fetchProductById(final int productId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("productId", String.valueOf(productId));

        Query query = getPmp().get().newQuery(SQL, GET_PRODUCT_BY_ID);
        query.setResultClass(Product.class);
        query.setUnique(true);
        Product productRecord = (Product) query.executeWithMap(parameters);

        return Optional.ofNullable(productRecord);
    }

    public ProductView createProduct(final Product product) {

        Product productRecord = persistAndFetch(product);
        return new ProductView(productRecord);
    }

    public boolean updateProduct(final String type, final int totalQuantity) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("type", String.valueOf(type));
        parameters.put("quantity", String.valueOf(totalQuantity));

        Query query = getPmp().get().newQuery(SQL, UPDATE_PRODUCT_BY_ID);
        query.setResultClass(Long.class);
        Long recordCount = (Long) query.executeWithMap(parameters);
        return recordCount == 1;
    }


    public boolean deleteProductById(final int productId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("productId", String.valueOf(productId));

        Query query = getPmp().get().newQuery(SQL, DELETE_PRODUCT_BY_ID);
        query.setResultClass(Long.class);
        Long recordCount = (Long) query.executeWithMap(parameters);

        return recordCount == 1;
    }

}
