package com.starbucks.service.mock;

import com.starbucks.model.Product;
import com.starbucks.service.ProductService;
import com.starbucks.view.ProductView;

import java.util.Map;

public class ProductServiceMockImpl implements ProductService {
    @Override
    public ProductView getProductById(final int productId) {
        return new ProductView(Product.sample().setId(productId));
    }

    @Override
    public ProductView createProduct(final Map<String, String> payload) {
        return new ProductView(Product.sample());
    }

    @Override
    public boolean updateProduct(final int productId, final Map<String, String> payload) {
        return true;
    }

    @Override
    public boolean deleteProduct(final int productId) {
        return true;
    }
}
