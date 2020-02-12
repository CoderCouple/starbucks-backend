package com.starbucks.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starbucks.Utils;
import com.starbucks.exception.InvalidFieldValueException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationPayload {

    @JsonProperty("firstName")
    @ApiModelProperty(value = "firstName", example = "fName")
    private String firstName;

    @JsonProperty("lastName")
    @ApiModelProperty(value = "lastName", example = "lName")
    private String lastName;

    @JsonProperty("email")
    @ApiModelProperty(value = "email", example = "test.user@gmail.com")
    private String email;

    @JsonProperty("password")
    @ApiModelProperty(value = "password", example = "Password@1")
    private String password;

    @JsonProperty("dob")
    @ApiModelProperty(value = "dob", example = "1971-07-28")
    private String dob;

    @JsonCreator
    public RegistrationPayload(@JsonProperty(value = "firstName", required = true) final String firstName,
                               @JsonProperty(value = "lastName", required = true) final String lastName,
                               @JsonProperty(value = "email", required = true) final String email,
                               @JsonProperty(value = "password", required = true) final String password,
                               @JsonProperty(value = "dob", required = true) final String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;

        if (!Utils.isValidEmailAddress(email)) {
            throw  new InvalidFieldValueException("Invalid email address format. Please try again!!!");
        }

        if (!Utils.isValidDate(dob)) {
            throw  new InvalidFieldValueException("Invalid date format. Please try again!!!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(final String dob) {
        this.dob = dob;
    }
}
