Feature: Using publicly available API's provided by Australia Post
Calculate the shipping cost
Depending on the source and destination postcode, select a delivery type
And then calculate the cost based on above values.

Scenario: Calculate the shipping cost depending on source, destination and delivery type
    Given User retrieves the postcode for the source and destination
    When User confirms the delivery type
    Then Calculate the total shipping cost value