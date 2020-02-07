package com.starbucks.view;

import com.starbucks.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public class UserView {

    private int id;
    private String guid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private int age;
    private boolean isActive;
    private Timestamp created;
    private Timestamp updated;

    public UserView(final User user) {
        this.id = user.getId();
        this.guid = user.getGuid();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dateOfBirth = user.getDateOfBirth().toString();
        this.age = getUserAgeFromDOB(user.getDateOfBirth());
        this.isActive = user.getIsActive();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
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

    public String getGuid() {
        return guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
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

        if (!(o instanceof UserView)) {
            return false;
        }

        UserView userView = (UserView) o;

        return new EqualsBuilder()
                .append(getId(), userView.getId())
                .append(getAge(), userView.getAge())
                .append(isActive(), userView.isActive())
                .append(getGuid(), userView.getGuid())
                .append(getFirstName(), userView.getFirstName())
                .append(getLastName(), userView.getLastName())
                .append(getEmail(), userView.getEmail())
                .append(getPassword(), userView.getPassword())
                .append(getDateOfBirth(), userView.getDateOfBirth())
                .append(getCreated(), userView.getCreated())
                .append(getUpdated(), userView.getUpdated())
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
                .append(getPassword())
                .append(getDateOfBirth())
                .append(getAge())
                .append(isActive())
                .append(getCreated())
                .append(getUpdated())
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
                .append("password", password)
                .append("dateOfBirth", dateOfBirth)
                .append("age", age)
                .append("isActive", isActive)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }
}
