package com.starbucks.stepdef;

import com.starbucks.util.ParsedResponse;
import com.starbucks.util.ShopState;
import com.starbucks.util.TestRunnerContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import javax.inject.Inject;

public class Response {

    @Inject
    private ParsedResponse response;

    @Inject
    private TestRunnerContext context;

    @Inject
    private ShopState shopState;

    @Then("^I get a response code of (\\d+)$")
    public void getResponseCode() throws Throwable {
        throw new PendingException();
    }

    @And("^I have a response of datatype (.*) with name (.*)$")
    public void getDatatypeWithName() throws Throwable {
        throw new PendingException();
    }

    @And("^Array element (\\d+) is an Object with (\\d+) fields$")
    public void readArrayElementWithField() throws Throwable {
        throw new PendingException();
    }

    @And("^I have a response of datatype (.*)$")
    public void readResponseDatatype() throws Throwable {
        throw new PendingException();
    }

    @And("^Object (.*) has (\\d+) fields$")
    public void countObjectFields() throws Throwable {
        throw new PendingException();
    }

}
