Feature: Pretraga
  Detaljniji opis

  Scenario Outline: Pretraga "<Column>"

    * I get test data from "TestData" "TestData2" "<Column>"
    * I am on polovni automobili app
    * I select vehicle type
    * I select brand
    * I select model
    * I click search
    * I should see search results

    Examples:
      | Column |
      | 1      |
      | 2      |
      | 3      |