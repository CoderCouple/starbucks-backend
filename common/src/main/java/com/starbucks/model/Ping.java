package com.starbucks.model;

import com.starbucks.dao.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;
import java.util.StringTokenizer;

@PersistenceCapable(detachable = "true", table = "Ping", objectIdClass = Ping.PK.class)
public class Ping  implements PersistentObject {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    private int id;

    @Persistent
    private String data;

    public Ping(final int id, final String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Ping setId(final int id) {
        this.id = id;
        return this;
    }

    public String getData() {
        return data;
    }

    public Ping setData(final String data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Ping)) {
            return false;
        }

        Ping ping = (Ping) o;

        return new EqualsBuilder()
                .append(getId(), ping.getId())
                .append(getData(), ping.getData())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getData())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("data", data)
                .toString();
    }

    @Override
    public Object primaryKey() {
        return id;
    }

    @Override
    public void markForDeletion() {

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
