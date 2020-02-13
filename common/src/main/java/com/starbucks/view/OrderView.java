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

    public OrderView setId(final int id) {
        this.id = id;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public OrderView setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public OrderView setUserId(final int userId) {
        this.userId = userId;
        return this;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public OrderView setInventoryId(final int inventoryId) {
        this.inventoryId = inventoryId;
        return this;
    }

    public Order.Status getStatus() {
        return status;
    }

    public OrderView setStatus(final Order.Status status) {
        this.status = status;
        return this;
    }

    public OrderView setStatus(final String status) {
        this.status = Order.Status.valueOf(status);
        return this;
    }

    public double getTotal() {
        return total;
    }

    public OrderView setTotal(final double total) {
        this.total = total;
        return this;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public OrderView setPurchaseDate(final Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Timestamp getCreated() {
        return created;
    }

    public OrderView setCreated(final Timestamp created) {
        this.created = created;
        return this;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public OrderView setUpdated(final Timestamp updated) {
        this.updated = updated;
        return this;
    }
}
