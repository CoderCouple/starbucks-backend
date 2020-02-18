package com.starbucks.model;

import com.starbucks.dao.PersistentObject;
import com.starbucks.util.Utils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.datanucleus.api.jdo.annotations.ReadOnly;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringTokenizer;

@PersistenceCapable(detachable = "true", table = "Product", objectIdClass = Product.PK.class)
public class Product  implements PersistentObject {

    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @PrimaryKey
    private int id;

    @Persistent
    @NotNull
    @Unique
    private Type type;

    @Persistent
    @NotNull
    private String name;

    @Persistent
    @NotNull
    private double cost;

    @Persistent
    @NotNull
    private int totalQuantity;

    @Persistent
    @NotNull
    private boolean isActive;

    @ReadOnly
    private Timestamp created;

    @ReadOnly
    private Timestamp updated;


    public enum Type {
        CAPPUCCINO("CAPPUCCINO"),
        ESPRESSO("ESPRESSO"),
        MOCHAS("MOCHAS"),
        MACCHIATOS("MACCHIATOS"),
        LATTES("LATTES");

        private String name;

        Type(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public int getId() {
        return id;
    }

    public Product setId(final int id) {
        this.id = id;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Product setType(final Type type) {
        this.type = type;
        return this;
    }

    public Product setType(final String type) {
        this.type = Type.valueOf(type);
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(final String name) {
        this.name = name;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Product setCost(final double cost) {
        this.cost = cost;
        return this;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public Product setTotalQuantity(final int totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Product setIsActive(final boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Product setCreated(final Timestamp created) {
        this.created = created;
        return this;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public Product setUpdated(final Timestamp updated) {
        this.updated = updated;
        return this;
    }

    public static Product sample() {
        return new Product()
                .setId(1)
                .setType(Type.LATTES)
                .setName("Mocha Latte")
                .setCost(4.5)
                .setTotalQuantity(10)
                .setIsActive(true)
                .setCreated(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setUpdated(Utils.getUTCTimestamp("2020-01-01 01:01:01"));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(getId(), product.getId())
                .append(getCost(), product.getCost())
                .append(getTotalQuantity(), product.getTotalQuantity())
                .append(getIsActive(), product.getIsActive())
                .append(getType(), product.getType())
                .append(getName(), product.getName())
                .append(getCreated(), product.getCreated())
                .append(getUpdated(), product.getUpdated())
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
                .append(getIsActive())
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

    @Override
    public Object primaryKey() {
        return getId();
    }

    @Override
    public void markForDeletion() {
        this.setIsActive(Boolean.FALSE);
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
            return new HashCodeBuilder()
                    .append(this.id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", this.id)
                    .toString();
        }
    }

}
