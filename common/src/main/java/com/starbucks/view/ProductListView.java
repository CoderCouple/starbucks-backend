package com.starbucks.view;

import com.starbucks.model.Product;

import java.util.List;

public class ProductListView {

    private List<ProductView> products;

    public ProductListView(final List<Product> products) {
        for (final Product product : products) {
            this.products.add(new ProductView(product));
        }
    }

    public List<ProductView> getProducts() {
        return products;
    }
}
