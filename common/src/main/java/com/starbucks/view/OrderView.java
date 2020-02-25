package com.starbucks.view;

import com.starbucks.model.Order;
import com.starbucks.util.CommonUtils;

public class OrderView {

    private int id;
    private String transactionId;
    private int userId;
    private Order.Status status;
    private double total;
    private String purchaseDate;
    private String created;
    private String updated;

    public OrderView(final Order order) {
        this.id = order.getId();
        this.transactionId = order.getTransactionId();
        this.userId = order.getUserId();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.purchaseDate = CommonUtils.getUTCDateTimeString(order.getPurchaseDate());
        this.created = CommonUtils.getUTCDateTimeString(order.getCreated());
        this.updated = CommonUtils.getUTCDateTimeString(order.getUpdated());
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

    public Order.Status getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}
