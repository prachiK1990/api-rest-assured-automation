
# User API test

Api test automation project for Unzer test assginment.


## Authors

- Prachi Kapoor


## Tech Stack

**Languages:** Java 8+

**Frameworks:** Cucumber BDD, Serenity, Junit

**Libraries:** RestAssured, Jackson , Slf4j

**Build tool:** Maven

**Plugin:** surefire, serenity-bdd-maven 
 



## Environment Variables

To run this project, you will need to add the following environment variables.

`JAVA_HOME`

`M2_HOME`


## Installation and running tests

Install with mvn. This will run tests as well.

```bash
  mvn clean verify
```

## Reports

Serenity report generated is available at target/site/serenity/index.html
	Use runner : ApiTestSuiteRunnerSerenity.java 
	Sample report screenshot in repository: sample_test_report_serenity.JPG

Cucumber generated reports are available in target folder under project root after the tests are run.
    Paths:  target/reports
    Use runner : ApiTestSuiteRunner.java



## Run Locally

Clone the project using below command and repo link

```bash
  git clone <insert-remote-repo-link-here>
```

Go to the project directory

```bash
  cd user-management-api-test-suite-bjvddx
```

Install dependencies and run tests

```bash
  mvn clean install
```



## Project Structure
#### Code 
    Path: src/test/java/
    Test Runner: com.api.runner.ApiTestSuiteRunner.java
    Step Definitions: com.api.step.definitions
    Utils: com.api.util
    Model : com.api.model

#### Config
	config.properties

#### Resources
    Path: src/test/resources
    Feature files: src/test/resources/features
    1. Create User : create/Create_1_User.feature
    2. Duplicate User: create/Create_2_DuplicateUser.feature
    3. Update: modify/Update_1_User.feature
    4. Delete: userDelete/delete_user.feature





## Improvement areas

    1. Some API responses could have been more accurate, like for read user when user does not exist.
    
    
### Assignment 
Please create a simple test suite that verifies functionality of a public user management API available at http://m3o.com/user/api.

### Some example flows that you cover in your tests
* Creation if a new user with mandatory/optional fields an verification that it was saved successfully
* Update of existing user
* Deletion of existing user
* Some error scenarios, like trying to create a user with duplicated id or missing /invalid information

### Our expectations for this task
* Please user Java or Kotlin as programming language and Maven or Gradle as a build tool.
* Use API testing library of your preference (for example, RestAssured, Retrofit or similar)
* Optional: think about reporting or some other way to make test results quick to interpret and test failures easier to investigate.
* Include description of your solution, choice of tool and instruction on how to execute the tests in the readme file.
* Please push your code to the provided git repository and submit your assignment within 5 calender days

### Tips
* Let us know if you have any questions - we're happy to help.
* Keep it simple, no need to spend too much time on this task or implement more than 5 test cases.
* Think about easiness of adding new test cases and making changes when building your test suite/framework.
* If there is something you would have improved or done differently if you had more time, please feel free to mention it in the readme file.



