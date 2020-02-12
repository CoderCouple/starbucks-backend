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
public class LoginPayload {

    @ApiModelProperty(value = "email", example = "test.user@gmail.com")
    @JsonProperty("email")
    private String email;

    @ApiModelProperty(value = "password", example = "Password@1")
    @JsonProperty("password")
    private String password;

    @JsonCreator
    public LoginPayload(@JsonProperty(value = "email", required = true) final String email,
                        @JsonProperty(value = "password", required = true) final String password) {
        this.email = email;
        this.password = password;

        if (!Utils.isValidEmailAddress(email)) {
            throw  new InvalidFieldValueException("Invalid email address format. Please try again!!!");
        }

        if (!Utils.isValidPassword(password)) {
            throw  new InvalidFieldValueException("Invalid password format. Please try again!!!");
        }

    }

    public String getEmail() {
        return email;
    }

    public LoginPayload setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginPayload setPassword(final String password) {
        this.password = password;
        return this;
    }
}
