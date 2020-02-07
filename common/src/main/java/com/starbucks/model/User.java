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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.StringTokenizer;

@PersistenceCapable(detachable = "true", table = "User", objectIdClass = User.PK.class)
public class User implements PersistentObject {

    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @PrimaryKey
    private int id;

    @Persistent
    @NotNull
    @Unique
    private String guid;

    @Persistent
    @NotNull
    private String firstName;

    @Persistent
    @NotNull
    private String lastName;

    @Persistent
    @NotNull
    @Unique
    private String email;

    @Persistent
    @NotNull
    private String password;

    @Persistent
    @NotNull
    private Date dateOfBirth;

    @Persistent
    @NotNull
    private boolean isActive;

    @ReadOnly
    private Timestamp created;

    @ReadOnly
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public User setId(final int id) {
        this.id = id;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public User setGuid(final String guid) {
        this.guid = guid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(final String password) {
        this.password = password;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public User setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public User setIsActive(final boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Timestamp getCreated() {
        return created;
    }

    public User setCreated(final Timestamp created) {
        this.created = created;
        return this;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public User setUpdated(final Timestamp updated) {
        this.updated = updated;
        return this;
    }

    public static User sample() {
        return new User()
                .setId(1)
                .setGuid("guid")
                .setFirstName("fname")
                .setLastName("lname")
                .setEmail("email")
                .setPassword("pwd")
                .setDateOfBirth(new Date(1580875529))
                .setIsActive(true)
                .setCreated(Utils.getUTCTimestamp("2020-01-01 01:01:01"))
                .setUpdated(Utils.getUTCTimestamp("2020-01-01 01:01:01"));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder()
                .append(getId(), user.getId())
                .append(isActive, user.isActive)
                .append(getGuid(), user.getGuid())
                .append(getFirstName(), user.getFirstName())
                .append(getLastName(), user.getLastName())
                .append(getEmail(), user.getEmail())
                .append(getPassword(), user.getPassword())
                .append(getDateOfBirth(), user.getDateOfBirth())
                .append(getCreated(), user.getCreated())
                .append(getUpdated(), user.getUpdated())
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
                .append(isActive)
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
                .append("isActive", isActive)
                .append("created", created)
                .append("updated", updated)
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
