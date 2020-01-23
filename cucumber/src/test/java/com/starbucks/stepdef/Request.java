package com.starbucks.stepdef;

import com.starbucks.util.RestClient;
import com.starbucks.util.ShopState;
import com.starbucks.util.TestRunnerContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import javax.inject.Inject;

@ScenarioScoped
public class Request {

    @Inject
    private TestRunnerContext testRunnerContext;

    @Inject
    private Response response;

    @Inject
    private RestClient client;

    @Inject
    private ShopState shopState;

    public Request() {
    }

    public void reset() {
    }


    @Given("^A web user$")
    public void getAWebUser() throws Throwable {
        throw new PendingException();
    }

    @Given("^An Admin user$")
    public void getAnAdminUser() throws Throwable {
        throw new PendingException();
    }

    @Given("^An unregistered web user$")
    public void getAnUnregisteredWebUser() throws Throwable {
        throw new PendingException();
    }

    @When("^I pass following parameters parameters:$")
    public void extractParameters() throws Throwable {
        throw new PendingException();
    }


    @And("^I do a (.*) query on endpoint (.*) with given parameters$")
    public void queryEndpointWithParameters() throws Throwable {
        throw new PendingException();
    }


    @When("^I do a (.*) query on endpoint (.*)$")
    public void queryEndpointWithOutParameters() throws Throwable {
        throw new PendingException();
    }
}
