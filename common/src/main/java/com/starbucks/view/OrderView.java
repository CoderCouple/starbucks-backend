package com.starbucks.view;

import com.starbucks.model.Order;

import java.sql.Timestamp;

public class OrderView {

    private int id;
    private String transactionId;
    private int userId;
    private int inventoryId;
    private Order.Status status;
    private double total;
    private Timestamp purchaseDate;
    private Timestamp created;
    private Timestamp updated;

    public OrderView(final Order order) {
        this.id = order.getId();
        this.transactionId = order.getTransactionId();
        this.userId = order.getUserId();
        this.inventoryId = order.getInventoryId();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.purchaseDate = order.getPurchaseDate();
        this.created = order.getCreated();
        this.updated = order.getUpdated();
    }

    public int getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public Order.Status getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }
}
