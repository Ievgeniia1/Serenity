Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Verification of put request
Given I create hardcoded order
When I post order with body
Then I extract order ID