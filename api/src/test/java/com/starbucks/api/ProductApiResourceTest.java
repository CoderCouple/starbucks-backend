package com.starbucks.api;

import com.google.gson.JsonParser;

public class ProductApiResourceTest extends BaseResourceTest {
    private static JsonParser parser = new JsonParser();
    @Override
    public Class getResourceClass() {
        return ProductApiResource.class;
    }

    @Override
    public String getResourcePath() {
        return "/product";
    }

}
