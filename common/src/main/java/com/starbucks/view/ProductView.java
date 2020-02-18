package com.starbucks.view;

import com.starbucks.model.Product;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

public class ProductView {

    private int id;
    private Product.Type type;
    private String name;
    private double cost;
    private int totalQuantity;
    private boolean isActive;
    private Timestamp created;
    private Timestamp updated;

    public ProductView(final Product product) {
        this.id = product.getId();
        this.type = product.getType();
        this.name = product.getName();
        this.cost = product.getCost();
        this.totalQuantity = product.getTotalQuantity();
        this.isActive = product.getIsActive();
        this.created = product.getCreated();
        this.updated = product.getUpdated();
    }

    public int getId() {
        return id;
    }

    public Product.Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ProductView)) {
            return false;
        }

        ProductView that = (ProductView) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCost(), that.getCost())
                .append(getTotalQuantity(), that.getTotalQuantity())
                .append(isActive(), that.isActive())
                .append(getType(), that.getType())
                .append(getName(), that.getName())
                .append(getCreated(), that.getCreated())
                .append(getUpdated(), that.getUpdated())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getType())
                .append(getName())
                .append(getCost())
                .append(getTotalQuantity())
                .append(isActive())
                .append(getCreated())
                .append(getUpdated())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("name", name)
                .append("cost", cost)
                .append("totalQuantity", totalQuantity)
                .append("isActive", isActive)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }
}
