
@tag
Feature: Purches the order from Ecommerce website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce page
 
 
  @Regression
  Scenario Outline: Positive Test of Submitting order
    Given Logged in with username<name> and password<password>
    When I add product <productName> to cart
    And Checkout<productName> and submit the order
    Then "THANKYOU FOR THE ORDER." massage is displayed on ConfirmationPage

    Examples: 
      | name                   |    password         | productName |
      | gardidipali1@gmail.com |    Dipali#1bhavsar  | ZARA COAT 3 |
      