package com.starbucks.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starbucks.exception.InvalidFieldValueException;
import com.starbucks.model.Product;
import com.starbucks.util.ApiUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPayload {

    @JsonProperty("type")
    @ApiModelProperty(value = "type", example = "LATTES", required = true, allowableValues = "CAPPUCCINO, ESPRESSO, MOCHAS, MACCHIATOS, LATTES")
    private Product.Type type;

    @JsonProperty("name")
    @ApiModelProperty(value = "name", example = "Smoked Butterscotch Latte", required = true)
    private String name;

    @JsonProperty("cost")
    @ApiModelProperty(value = "cost", example = "4.5", required = true)
    private double cost;

    @JsonProperty("totalQuantity")
    @ApiModelProperty(value = "totalQuantity", example = "10", required = true)
    private int totalQuantity;


    @JsonCreator
    public ProductPayload(@JsonProperty(value = "type", required = true) final String type,
                          @JsonProperty(value = "name", required = true) final String name,
                          @JsonProperty(value = "cost", required = true) final double cost,
                          @JsonProperty(value = "totalQuantity", required = true) final int totalQuantity) {


        if (!ApiUtils.isValidProductType(type)) {
            throw  new InvalidFieldValueException("Invalid product type. Please try again!!!");
        }

        this.type = Product.Type.valueOf(type);
        this.name = name;
        this.cost = cost;
        this.totalQuantity = totalQuantity;
    }
}
