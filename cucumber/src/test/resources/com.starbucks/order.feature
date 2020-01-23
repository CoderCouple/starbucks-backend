Feature: Order related features
  Admin of starbucks are those who perform daily admin task such as
    1)filling the inventory
    2)viewing various orders by different filters
  As a Admin I should be able to view orders by different filters.
  An Admin should also be able to create, read, update, and delete the inventory.
  Orders also status (PLACED, PENDING, COMPLETE, CANCELED).
  A Admin should be able to filter order by various filters such as
    2)1)Order by Id
    3)All orders
    4)Order by UserId
    5)Order by Status


  Scenario: Create an Order
    Given A web user
    When I pass following parameters parameters:
    """
    { "userId":123456 ,"ItemName":"Mocha", "ItemType":"Coffee", "Quantity" : 2}
    """
    And I do a POST query on endpoint /api/v1/order with given parameters
    Then I get a response code of 201
    And I have a response of datatype Order
    And Object Order has 5 fields


  Scenario: Check status of the Order
    Given A web user
    When I do a GET query on endpoint /api/v1/order/345 with given parameters
    Then I get a response code of 200
    And I have a response of datatype Order
    And Object Order has 6 fields


  Scenario: Cancel the Order
    Given A web user
    When I pass following parameters parameters:
    """
    { "orderId": 345,"status":"CANCELED"}
    """
    And I do a PUT query on endpoint /api/v1/order with given parameters
    Then I get a response code of 200
    And I have a response of datatype Order
    And Object Order has 6 fields


  Scenario: Delete the Order
    Given A web user
    When I do a DELETE query on endpoint /api/v1/order/345
    Then I get a response code of 200
    And I have a response of datatype Order
    And Object Order has 6 fields
