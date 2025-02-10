Feature: User Registration

  @Positive
  Scenario: TC1 - Register with valid details
    Given I navigate to the registration page
    When I register with:
      | FirstName | LastName | Email               | Password  |
      | John      | Doe      | jonn12.doe@test.com | Pass123!  |
    Then I should see the registration message "Your registration completed"
    And close the browser

  @Negative
  Scenario: TC2 - Register with existing email
    Given I navigate to the registration page
    When I register with existing email "john.doe@test.com"
    Then I should see the registration message "The specified email already exists"
    And close the browser