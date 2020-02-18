package com.starbucks.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starbucks.util.Utils;
import com.starbucks.exception.InvalidFieldValueException;
import com.starbucks.exception.MissingRequiredFieldException;
import com.starbucks.model.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPayload {


    private static final String PRODUCT_ID = "productId";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String COST = "cost";
    private static final String QUANTITY = "quantity";

    @JsonProperty("guid")
    @ApiModelProperty(value = "guid", example = "GUID1", required = true)
    private String guid;

    @JsonProperty(value = "userId")
    @ApiModelProperty(value = "userId", example = "1", required = true)
    private int userId;

    @JsonProperty("total")
    @ApiModelProperty(value = "total", example = "100", required = true)
    private double total;

    @JsonProperty("status")
    @ApiModelProperty(value = "status", example = "PLACED", allowableValues = "PLACED, BLOCKED, CANCELED, COMPLETED", required = true)
    private Order.Status status;

    @JsonProperty("purchaseDate")
    @ApiModelProperty(value = "purchaseDate", example = "1971-07-28 : 01:01:01", required = true)
    private String purchaseDate;

    @JsonProperty("lineItems")
    @ApiModelProperty(value = "lineItems", required = true)
    private List<LineItem> lineItems;


    @JsonCreator
    public OrderPayload(@JsonProperty(value = "guid", required = true) final String guid,
                        @JsonProperty(value = "userId", required = true) final int userId,
                        @JsonProperty(value = "total", required = true) final double total,
                        @JsonProperty(value = "status", required = true) final String status,
                        @JsonProperty(value = "purchaseDate", required = true) final String purchaseDate,
                        @JsonProperty(value = "lineItems", required = true) final List<Map<String, String>> lineItems) {

        if (!Utils.isValidOrderStatus(status)) {
            throw  new InvalidFieldValueException("Invalid order status. Please try again!!!");
        }

        this.guid = guid;
        this.userId = userId;
        this.total = total;
        this.status = Order.Status.valueOf(status);
        this.purchaseDate = purchaseDate;

        List<LineItem> lineItemsParams = new ArrayList<>();
        for (final Map<String, String> map : lineItems) {
            lineItemsParams.add(new LineItem(map));
        }
        this.lineItems = lineItemsParams;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LineItem {

        @JsonProperty("productId")
        @ApiModelProperty(value = "productId", example = "1", required = true)
        private String productId;

        @JsonProperty("name")
        @ApiModelProperty(value = "name", example = "Smoked Butterscotch Latte", required = true)
        private String name;

        @JsonProperty("type")
        @ApiModelProperty(value = "type", example = "LATTES", required = true, allowableValues = "CAPPUCCINO, ESPRESSO, MOCHAS, MACCHIATOS, LATTES")
        private String type;

        @JsonProperty("cost")
        @ApiModelProperty(value = "cost", example = "5.5", required = true)
        private double cost;

        @JsonProperty("quantity")
        @ApiModelProperty(value = "quantity", example = "4", required = true)
        private int quantity;

        public LineItem() {
        }

        public LineItem(final Map<String, String> data) {
            if (!data.containsKey(PRODUCT_ID)) {
                throw new MissingRequiredFieldException("Missing Required Field : " + PRODUCT_ID);
            }

            if (!data.containsKey(NAME)) {
                throw new MissingRequiredFieldException("Missing Required Field : " + NAME);
            }

            if (!data.containsKey(TYPE)) {
                throw new MissingRequiredFieldException("Missing Required Field : " + TYPE);
            }

            if (!data.containsKey(COST)) {
                throw new MissingRequiredFieldException("Missing Required Field : " + COST);
            }

            if (!data.containsKey(QUANTITY)) {
                throw new MissingRequiredFieldException("Missing Required Field : " + QUANTITY);
            }

            if (!Utils.isValidProductType(type)) {
                throw  new InvalidFieldValueException("Invalid product type. Please try again!!!");
            }

            this.productId = data.get(PRODUCT_ID);
            this.name = data.get(NAME);
            this.type = data.get(TYPE);
            this.cost = Double.valueOf(data.get(COST));
            this.quantity = Integer.valueOf(data.get(QUANTITY));
        }
    }
}
