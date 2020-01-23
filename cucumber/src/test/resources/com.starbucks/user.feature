Feature: User related features
  User of starbucks are those who user starbucks to order drinks such as tea/coffee.
  A user can register/sign up on the website and order his/her drink through the online portal
  As a user I should be able to Create, Read, Update and Delete orders.
  A users should also be able to check his/her order history.
  Orders also status (PLACED, PENDING, COMPLETE, CANCELED). A user should be able to check the status of his or her order.

  Scenario: Register User
    Given An unregistered web user
    When I pass following parameters parameters:
    """
    { "firstName" : "Sunil", "lastName" : "Tiwari", "email" : "sunil28071987@gmail.com", "password":"Sunil123", "phoneNumber" : "+16692492259"}
    """
    And I do a POST query on endpoint /api/v1/user/register with given parameters
    Then I get a response code of 201
    And I have a response of datatype User
    And Object Order has 5 fields


  Scenario: User Login
    Given A web user
    When I pass following parameters parameters:
    """
    { "username" : "sunil28071987@gmail.com", "password" : "Sunil123"}
    """
    And I do a POST query on endpoint /api/v1/user/login with given parameters
    Then I get a response code of 200
    And I have a response of datatype User
    And Object User has 5 fields


  Scenario: User Logout
    Given A web user
    When I do a POST query on endpoint /api/v1/user/logout/123456
    Then I get a response code of 200


  Scenario: Get user details
    Given A web user
    When I do a GET query on endpoint /api/v1/user/123456
    Then I get a response code of 200
    And I have a response of datatype Order
    And Object Order has 6 fields


  Scenario: Check user history
    Given A web user
    When I do a GET query on endpoint /api/v1/user/history/123456
    Then I get a response code of 200
    And I have a response of datatype Array
    And Array element 0 is an Object with 6 fields

