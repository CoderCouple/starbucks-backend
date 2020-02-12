package com.starbucks.view;

import com.starbucks.model.Order;
import com.starbucks.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserProfileView {

    private int id;
    private String guid;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private int age;
    private boolean isActive;
    private Timestamp created;
    private Timestamp updated;
    private List<Order> orders = new ArrayList<>();

    public UserProfileView(final User user, final List<Order> orderList) {
        this.id = user.getId();
        this.guid = user.getGuid();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.dateOfBirth = user.getDateOfBirth().toString();
        this.age = getUserAgeFromDOB(user.getDateOfBirth());
        this.isActive = user.getIsActive();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
        this.orders.addAll(orderList);
    }

    private int getUserAgeFromDOB(final Date dateOfBirth) {
        if (dateOfBirth != null) {
            return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public UserProfileView setId(final int id) {
        this.id = id;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public UserProfileView setGuid(final String guid) {
        this.guid = guid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileView setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileView setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public UserProfileView setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserProfileView setAge(final int age) {
        this.age = age;
        return this;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public UserProfileView setIsActive(final boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getCreated() {
        return created;
    }

    public UserProfileView setCreated(final Timestamp created) {
        this.created = created;
        return this;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public UserProfileView setUpdated(final Timestamp updated) {
        this.updated = updated;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public UserProfileView setOrders(final List<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserProfileView)) {
            return false;
        }

        UserProfileView that = (UserProfileView) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getAge(), that.getAge())
                .append(getIsActive(), that.getIsActive())
                .append(getGuid(), that.getGuid())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getEmail(), that.getEmail())
                .append(getDateOfBirth(), that.getDateOfBirth())
                .append(getCreated(), that.getCreated())
                .append(getUpdated(), that.getUpdated())
                .append(getOrders(), that.getOrders())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getGuid())
                .append(getFirstName())
                .append(getLastName())
                .append(getEmail())
                .append(getDateOfBirth())
                .append(getAge())
                .append(getIsActive())
                .append(getCreated())
                .append(getUpdated())
                .append(getOrders())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("guid", guid)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("dateOfBirth", dateOfBirth)
                .append("age", age)
                .append("isActive", isActive)
                .append("created", created)
                .append("updated", updated)
                .append("orders", orders)
                .toString();
    }
}
