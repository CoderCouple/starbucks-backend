package com.starbucks.model;

import com.starbucks.dao.PersistentObject;
import com.starbucks.util.Utils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.datanucleus.api.jdo.annotations.ReadOnly;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringTokenizer;

@PersistenceCapable(detachable = "true",  table = "User", objectIdClass = Order.PK.class)
public class Order implements PersistentObject {

    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @PrimaryKey
    private int id;

    @Persistent
    @NotNull
    @Unique
    private String transactionId;

    @Persistent
    @NotNull
    private int userId;

    @Persistent
    @NotNull
    private int inventoryId;

    @Persistent
    @NotNull
    @Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "getValue")
    private Status status;

    @Persistent
    private double total;

    @Persistent
    private Timestamp purchaseDate;

    @ReadOnly
    private Timestamp created;

    @ReadOnly
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public Order setId(final int id) {
        this.id = id;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Order setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Order setUserId(final int userId) {
        this.userId = userId;
        return this;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public Order setInventoryId(final int inventoryId) {
        this.inventoryId = inventoryId;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Order setStatus(final Order.Status status) {
        this.status = status;
        return this;
    }

    public Order setStatus(final String status) {
        this.status = Status.valueOf(status);
        return this;
    }

    public double getTotal() {
        return total;
    }

    public Order setTotal(final double total) {
        this.total = total;
        return this;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public Order setPurchaseDate(final Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Order setCreated(final Timestamp created) {
        this.created = created;
        return this;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public Order setUpdated(final Timestamp updated) {
        this.updated = updated;
        return this;
    }

    public enum Status {
        PLACED("PLACED"),
        BLOCKED("BLOCKED"),
        CANCELED("CANCELED"),
        COMPLETED("COMPLETED");

        private String value;

        Status(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public static Order sample() {
        return new Order()
                .setId(1)
                .setTransactionId("TRNX123456")
                .setUserId(1)
                .setInventoryId(1)
                .setStatus(Status.PLACED)
                .setTotal(100)
                .setPurchaseDate(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setCreated(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setUpdated(Utils.getUTCTimestamp("2020-01-01 01:01:01"));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(getId(), order.getId())
                .append(getUserId(), order.getUserId())
                .append(getInventoryId(), order.getInventoryId())
                .append(getTotal(), order.getTotal())
                .append(getTransactionId(), order.getTransactionId())
                .append(getStatus(), order.getStatus())
                .append(getPurchaseDate(), order.getPurchaseDate())
                .append(getCreated(), order.getCreated())
                .append(getUpdated(), order.getUpdated())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getTransactionId())
                .append(getUserId())
                .append(getInventoryId())
                .append(getStatus())
                .append(getTotal())
                .append(getPurchaseDate())
                .append(getCreated())
                .append(getUpdated())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("transactionId", transactionId)
                .append("userId", userId)
                .append("inventoryId", inventoryId)
                .append("status", status)
                .append("total", total)
                .append("purchaseDate", purchaseDate)
                .append("createDate", created)
                .append("updateDate", updated)
                .toString();
    }

    @Override
    public Object primaryKey() {
        return getId();
    }

    public static class PK implements Serializable {

        public int id;

        public PK() {
        }

        public PK(final String value) {
            StringTokenizer token = new StringTokenizer(value, DELIMITER);
            this.id = Integer.getInteger(token.nextToken());
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            Ping.PK rhs = (Ping.PK) obj;
            return new EqualsBuilder()
                    .append(this.id, rhs.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", id)
                    .toString();
        }
    }
}
