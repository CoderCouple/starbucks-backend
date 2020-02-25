package com.starbucks.service.impl;

import com.starbucks.exception.NotFoundException;
import com.starbucks.exception.StarbucksApiException;
import com.starbucks.model.Product;
import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.service.ProductService;
import com.starbucks.view.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private DaoProvider daoProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    public ProductServiceImpl(final DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @Override
    public ProductView getProductById(final int productId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            Optional<Product> product = daoProvider.getDaoFactory().getProductDao(pmp).fetchProductById(productId);
            if (!product.isPresent()) {
                throw new NotFoundException("Product not found in DB");
            }
            return new ProductView(product.get());
        }
    }

    @Override
    public ProductView createProduct(final Map<String, String> payload) {
        Product product = new Product()
                .setType(payload.get("type"))
                .setName(payload.get("name"))
                .setCost(Double.valueOf(payload.get("cost")))
                .setTotalQuantity(Integer.valueOf(payload.get("totalQuantity")))
                .setIsActive(true);

        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            return daoProvider.getDaoFactory().getProductDao(pmp).createProduct(product);
        } catch (final Exception ex) {
            throw new StarbucksApiException("Unable to create product in DB");
        }
    }

    @Override
    public boolean updateProduct(final String type, final Map<String, String> payload) {
        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            int totaQuantity = Integer.valueOf(payload.get("totalQuantity"));
            boolean isSuccessful = daoProvider.getDaoFactory().getProductDao(pmp).updateProduct(type, totaQuantity);
            if (!isSuccessful) {
                throw new StarbucksApiException("Failed to update product in DB");
            }
            return isSuccessful;
        }
    }

    @Override
    public boolean deleteProduct(final int productId) {
        try (final PersistenceManagerProvider pmp = daoProvider.getWritePmp()) {
            boolean isSuccessful = daoProvider.getDaoFactory().getProductDao(pmp).deleteProductById(productId);
            if (!isSuccessful) {
                throw new StarbucksApiException("Failed to delete product in DB");
            }
            return isSuccessful;
        }
    }
}
