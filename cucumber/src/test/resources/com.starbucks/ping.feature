Feature: Just a simple Ping
  This a sample test for a ping api

  Scenario: Ping
    Given A web user
    When I do a GET query on endpoint /api/v1/ping
    Then I get a response code of 200