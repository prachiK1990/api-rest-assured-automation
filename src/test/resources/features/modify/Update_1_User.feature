 @userAPI
Feature: To Update user using user API
  
  Backgroud: Update user
  @updateUser
  Scenario: To update unique user
    #Given I want to write a step with precondition
    When create a test user "/v1/user/Create"
   	When call update api and update username email "/v1/user/Update"
   	Then call read api by id and verify updated email and username "/v1/user/Read"