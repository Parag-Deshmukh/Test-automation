Feature: User Registration

  @Positive
  Scenario: TC1 - Register with valid details
    Given I navigate to the registration page
    When I register with:
      | FirstName | LastName | Email               | Password  |
      | John      | Doe      | john12.doe@test.com   | Pass123!  |
    Then I should see "Your registration completed"
    And close the browser

  @Negative
  Scenario: TC2 - Register with existing email
    Given I navigate to the registration page
    When I register with existing email "john.doe@test.com"
    Then I should see "The specified email already exists"
    And close the browser

  @Negative
  Scenario: TC3 - Register with missing mandatory fields
    Given I navigate to the registration page
    When I leave all fields empty and submit
    Then I should see "First name is required"
      And I should see "Last name is required"
      And I should see "Email is required"
      And I should see "Password is required"
    And close the browser

  @Negative
  Scenario: TC4 - Register with invalid email format
    Given I navigate to the registration page
    When I enter invalid email "user@.com"
    Then I should see "Wrong email"
    And close the browser