Scenario: Looking for the flights from 'YVR' to 'YYZ' and triptype RT
Given The user opened the first search page
When the user is looking for flight from 'YYZ' route and to 'YVR' destination and 'RT' trip type
Then the user should see select page is opened after submit form
Then Verify the routes are present

Scenario: Looking up the definition of 'pear' and triptype OW
Given The user opened the first search page
When the user is looking for flight from 'YYZ' route and to 'YVR' destination and 'OW' trip type
Then the user should see select page is opened after submit form
Then Verify the routes are present
