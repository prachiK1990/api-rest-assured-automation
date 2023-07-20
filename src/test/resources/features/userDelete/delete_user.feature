 @userAPI
Feature: To Delete user using user API
  
  Backgroud: Delete user
  @deleteUser
  Scenario: To delete unique user
   When call create user api for test "/v1/user/Create"
   Then call delete api and delete a user account by id "/v1/user/Delete"
   And call read api by id and verify deleted email and username "/v1/user/Read"