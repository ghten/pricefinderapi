Feature: Price Finder API

  Scenario: Test 1: Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given Service is running
    When I request price for the product 35455 of the brand 1 on date "2020-06-14T10:00:00"
    Then returned price must be 35.50

  Scenario: Test 2: Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given Service is running
    When I request price for the product 35455 of the brand 1 on date "2020-06-14T16:00:00"
    Then returned price must be 25.45

  Scenario: Test 3: Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given Service is running
    When I request price for the product 35455 of the brand 1 on date "2020-06-14T21:00:00"
    Then returned price must be 35.50

  Scenario: Test 4: Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    Given Service is running
    When I request price for the product 35455 of the brand 1 on date "2020-06-15T10:00:00"
    Then returned price must be 30.50

  Scenario: Test 5: Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    Given Service is running
    When I request price for the product 35455 of the brand 1 on date "2020-06-16T21:00:00"
    Then returned price must be 38.95



