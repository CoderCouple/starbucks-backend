package com.starbucks.model;

import com.starbucks.persistance.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringTokenizer;

@PersistenceCapable(detachable = "true", table = "LineItem", objectIdClass = LineItem.PK.class)
public class LineItem  implements PersistentObject {

    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @PrimaryKey
    private int id;

    @Persistent
    @NotNull
    private int orderId;

    @Persistent
    @NotNull
    private int productId;

    @Persistent
    @NotNull
    private int quantity;


    @Override
    public Object primaryKey() {
        return null;
    }

    public int getId() {
        return id;
    }

    public LineItem setId(final int id) {
        this.id = id;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public LineItem setOrderId(final int orderId) {
        this.orderId = orderId;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public LineItem setProductId(final int productId) {
        this.productId = productId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public LineItem setQuantity(final int quantity) {
        this.quantity = quantity;
        return this;
    }

    public static LineItem sample() {
        return new LineItem()
                .setId(1)
                .setOrderId(1)
                .setProductId(1)
                .setQuantity(10);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof LineItem)) {
            return false;
        }

        LineItem lineItem = (LineItem) o;

        return new EqualsBuilder()
                .append(getId(), lineItem.getId())
                .append(getOrderId(), lineItem.getOrderId())
                .append(getProductId(), lineItem.getProductId())
                .append(getQuantity(), lineItem.getQuantity())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getOrderId())
                .append(getProductId())
                .append(getQuantity())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("orderId", orderId)
                .append("productId", productId)
                .append("quantity", quantity)
                .toString();
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
