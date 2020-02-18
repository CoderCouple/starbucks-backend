package com.starbucks.view;

import com.starbucks.model.Ping;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PingView {

    private int id;
    private String data;

    public PingView(final Ping ping) {
        this.id = ping.getId();
        this.data = ping.getData();
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PingView)) {
            return false;
        }

        PingView pingView = (PingView) o;

        return new EqualsBuilder()
                .append(getId(), pingView.getId())
                .append(getData(), pingView.getData())
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
}
