Feature: User Login

  @Positive
  Scenario: TC11 - Login with valid credentials
    Given I navigate to the login page
    When I login with "john98.doe@test.com" and "Pass123!"
    Then I should see "Log out"
    And close the browser

  @Negative
  Scenario: TC13 - Login with incorrect email
    Given I navigate to the login page
    When I login with "invalid@test.com" and "Pass123!"
    Then I should see "Login was unsuccessful"
    And close the browser

  @Negative
  Scenario: TC14 - Login with incorrect password
    Given I navigate to the login page
    When I login with "john.doe@test.com" and "wrongpass"
    Then I should see "Login was unsuccessful"
    And close the browser

  @Negative
  Scenario: TC17 - Account lockout after multiple failed attempts
    Given I navigate to the login page
    When I attempt to login 5 times with invalid credentials
    Then I should see "Your account is locked"
    And close the browser