@userAPI
Feature: To Create new user using user API
  
  Backgroud: Create new user
  @createNewUser
  Scenario: To Create new unique user
    #Given I want to write a step with precondition
    When call create user api "/v1/user/Create"
    Then user should get response code 200
    And verify create api response
    And call read api by id and verify user should be created "/v1/user/Read"
    And verify read response schema "schemas/readUserSchema.json"