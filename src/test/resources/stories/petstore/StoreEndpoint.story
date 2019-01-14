Meta:

Narrative:
As a user
I want to test CRUD methods on Store Endpoint
So that I can see the results

Scenario: Verification of put request
Meta:
!--@skip
!--@ignored true
Given I create default order
When I update order
Then the status code is 405


Scenario: Verification of post request
Meta:
!--@skip
!--@ignored true
When I create default order
And I retrieve the order by ID
Then the status code is 200
And ID of created order equals ID of default order


Scenario: Verification of get request
Meta:
!--@skip
!--@ignored true
Given I create default order
When I retrieve the order by ID
Then ID of created order equals ID of default order
And the status code is 200



Scenario: Verification of delete request
Meta:
!--@skip
!--@ignored true
Given I create default order
And I retrieve the order by ID
When I delete the order by ID
And I get the response by ID
Then the status code is 404
