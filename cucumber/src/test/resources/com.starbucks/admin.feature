Feature: Admin related features
  Admin of starbucks are those who perform daily admin task such as
    1) Filling the inventory
    2) Viewing various orders by different filters
    3) Viewing all the users
  An Admin should also be able to create, read, update, and delete the inventory.

  Scenario: Get all Orders
    Given An Admin user
    When I do a GET query on endpoint /api/v1/admin/orders
    Then I get a response code of 200
    And I have a response of datatype Array
    And Array element 0 is an Object with 6 fields

  Scenario: Get all Users
    Given An Admin user
    When I do a GET query on endpoint /api/v1/admin/users
    Then I get a response code of 200
    And I have a response of datatype Array
    And Array element 0 is an Object with 6 fields

  Scenario: Get orders by filter
    Given An Admin user
    When I pass following parameters parameters:
    """
    { "status":"CANCELED"}
    """
    And I do a PUT query on endpoint /api/v1/order with given parameters
    Then I get a response code of 200
    And I have a response of datatype Array
    And Array element 0 is an Object with 6 fields