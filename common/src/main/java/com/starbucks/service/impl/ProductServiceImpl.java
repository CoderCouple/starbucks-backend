package com.starbucks.service.impl;

import com.starbucks.dao.ProductDao;
import com.starbucks.exception.NotFoundException;
import com.starbucks.exception.StarbucksApiException;
import com.starbucks.model.Product;
import com.starbucks.service.ProductService;
import com.starbucks.view.ProductView;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Inject
    public ProductServiceImpl(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductView getProductById(final int productId) {
        Optional<Product> product = productDao.fetchProductById(productId);
        if (!product.isPresent()) {
            throw new NotFoundException("Product not found in DB");
        }
        return new ProductView(product.get());
    }

    @Override
    public ProductView createProduct(final Map<String, String> payload) {
        ProductView productView = null;
        Product product = new Product()
                .setType(payload.get("type"))
                .setName(payload.get("name"))
                .setCost(Double.valueOf(payload.get("cost")))
                .setTotalQuantity(Integer.valueOf(payload.get("totalQuantity")))
                .setIsActive(true);

        try {
            productView = productDao.createProduct(product);
        } catch (final Exception ex) {
            throw new StarbucksApiException("Unable to create product in DB");
        }

        return productView;
    }

    @Override
    public boolean updateProduct(final int productId, final Map<String, String> payload) {
        int totaQuantity = Integer.valueOf(payload.get("totalQuantity"));
        boolean isSuccessful = productDao.updateProduct(productId, totaQuantity);
        if (!isSuccessful) {
            throw new StarbucksApiException("Failed to update product in DB");
        }
        return isSuccessful;
    }

    @Override
    public boolean deleteProduct(final int productId) {
        boolean isSuccessful = productDao.deleteProductById(productId);
        if (!isSuccessful) {
            throw new StarbucksApiException("Failed to delete product in DB");
        }
        return isSuccessful;
    }
}
