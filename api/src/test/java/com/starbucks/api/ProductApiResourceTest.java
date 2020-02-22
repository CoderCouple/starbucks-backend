package com.starbucks.api;

import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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

    @DataProvider
    public Object[][] getPingRequestGood() {
        return new Object[][]{
                {"sunil"},
                {"Payal"}
        };
    }

    @Test(dataProvider = "getPingRequestGood")
    public void testGetPingRequestGood(final String username) {
        String expectedResult = getResourceAsString("ping-response.json");
        Response response = get();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(parser.parse(response.readEntity(String.class)), parser.parse(expectedResult));

    }
}
