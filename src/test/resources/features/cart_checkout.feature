@Checkout
Feature: Product Checkout Flow - DemoWebShop

 @Checkout @Positive
Scenario: Checkout a product with valid shipping and payment details
    Given I am logged in as "john99.doe@test.com" with password "Pass123!"
    When I search for "Smartphone" and add to cart
    And I proceed to checkout with:
      | FirstName | John          |
      | LastName  | Doe           |
      | Email     | john.doe@example.com |
      | Country   | United States |
      | City      | New York      |
      | Address1  | 123 Main St   |
      | ZipCode   | 10001         |
      | Phone     | 1234567890    |
    And I handle shipping and payment methods
    And I enter payment details:
      | CardholderName | John Doe         |
      | CardNumber     | 4111111111111111 |
      | ExpirationDate | 12/2025          |
      | CVV            | 123             |
    Then I should see order confirmation "Your order has been successfully processed!"
