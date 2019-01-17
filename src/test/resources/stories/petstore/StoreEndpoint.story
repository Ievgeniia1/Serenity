Meta:

Narrative:
As a user
I want to test CRUD methods on Store Endpoint
So that I can see the results


Scenario: Verification of post request
!--Meta:
!--@skip
!--@ignored true
Given I create default order
When I get order by id
Then The status code is 200


Scenario: Verification of get request
!--Meta:
!--@skip
!--@ignored true
Given I create default order
When I get order by id
Then The status code is 200


Scenario: Verification of put request
!--Meta:
!--@skip
!--@ignored true
Given I create default order
When I set quantity 10 to created order
Then The status code is 405
And Parameters (ID,Status) of created order equals parameters  of default order


Scenario: Verification of delete request
!--Meta:
!--@skip
!--@ignored true
Given I create default order
When I delete order by id
And I get order by id
Then The status code is 404

