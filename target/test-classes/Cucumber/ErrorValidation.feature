
@tag
Feature: ErrorValidation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Logged in with username<name> and password<password>
    Then "Incorrect email or password." massage is displayed

    Examples: 
      | name                   | password        |   
      | gardidipali1@gmail.com | Dipali#2bhavsar |  
      
