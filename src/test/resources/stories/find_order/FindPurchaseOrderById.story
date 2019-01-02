Meta:

Narrative:
As a user
I want to find purchase order by ID
and I want to able deleting order by ID
So that I can delete orders

Scenario: find purchase order by ID
Given the order is created
When the user finds purchase order by <ID>
And the user deletes purchase order by <ID>
Then the order by <ID> not found

Examples:
|ID|
|8972|


