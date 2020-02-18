package com.starbucks.service;

import com.starbucks.view.ProductView;

import java.util.Map;

public interface ProductService {

    ProductView getProductById(final int orderId);

    ProductView createProduct(final Map<String, String> payload);

    boolean updateProduct(final int productId, final Map<String, String> payload);

    boolean deleteProduct(final int orderId);

}
