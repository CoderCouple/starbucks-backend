package com.starbucks.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShopState {
    private int orderId;
    private int userId;
    private int inventoryId;

    public int getOrderId() {
        return orderId;
    }

    public ShopState setOrderId(final int orderId) {
        this.orderId = orderId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public ShopState setUserId(final int userId) {
        this.userId = userId;
        return this;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public ShopState setInventoryId(final int inventoryId) {
        this.inventoryId = inventoryId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("userId", userId)
                .append("inventoryId", inventoryId)
                .toString();
    }
}
