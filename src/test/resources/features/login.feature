Feature: User Login
  As a user
  I want to log in to the application
  So that I can access protected features and pages

  @Positive
  Scenario: TC4 - Login with valid credentials
    Given I navigate to the login page
    When I login with "john98.doe@test.com" and "Pass123!"
    Then I should see the login message "Log out"
    And close the login browser

  @Negative
  Scenario: TC13 - Login with incorrect email
    Given I navigate to the login page
    When I login with "invalid@test.com" and "Pass123!"
    Then I should see the login message "Login was unsuccessful"
    And close the browser