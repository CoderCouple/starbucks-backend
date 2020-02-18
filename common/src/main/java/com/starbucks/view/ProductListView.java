package com.starbucks.view;

import com.starbucks.model.Product;

import java.util.List;

public class ProductListView {

    private List<Product> products;

    public ProductListView(final List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
