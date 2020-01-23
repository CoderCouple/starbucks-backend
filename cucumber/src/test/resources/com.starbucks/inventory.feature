Feature: Admin related features
  Admin of starbucks are those who perform daily admin task such as
  1)filling the inventory
  2)viewing various orders by different filters
  An Admin should also be able to create, read, update, and delete the inventory.

  Scenario: Create an Inventory
    Given An Admin user
    When I pass following parameters parameters:
    """
    { "ItemName":"Mocha", "ItemType":"Coffee", "brand":"Folger's Classic Roast", "Quantity" : 20}
    """
    And I do a POST query on endpoint /api/v1/inventory with given parameters
    Then I get a response code of 201
    And I have a response of datatype Inventory
    And Object Inventory has 5 fields


  Scenario: Read the Inventory
    Given An Admin user
    When I do a GET query on endpoint /api/v1/inventory/56789
    Then I get a response code of 200
    And I have a response of datatype Inventory
    And Object Inventory has 5 fields


  Scenario: Update the Inventory
    Given An Admin user
    When I pass following parameters parameters:
    """
    { "inventoryId": 56789,"brand":"Nescafe Taster's Choice House Blend"}
    """
    And I do a PUT query on endpoint /api/v1/inventory with given parameters
    Then I get a response code of 200
    And I have a response of datatype Inventory
    And Object Inventory has 5 fields


  Scenario: Delete the Inventory
    Given An Admin user
    When I do a DELETE query on endpoint /api/v1/inventory/56789
    Then I get a response code of 200
    And I have a response of datatype Inventory
    And Object Inventory has 5 fields

