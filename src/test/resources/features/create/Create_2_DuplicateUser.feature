@duplicateUserAPI
Feature: To create duplicate user
  
  Backgroud: Create duplicate user
  @createDuplicateUser
  Scenario Outline: To Create duplicate user
    #Given I want to write a step with precondition
    When call create duplicate user api "/v1/user/Create"
    |id|username|email|password|
    |<id>|<username>|<email>|<password>|
    
    Then user should get response code 400 for duplicate record
    And user verifies error message for duplicate record
  
Examples:
 |id|username|email|password|
 |user-1|joe|joe@example.com|Password1|