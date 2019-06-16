Feature: Simple tests for Last.fm website

  Scenario Outline: Login
    When the user enter "<login>" and "<password>"
    Then user can find his "<login>" name at the login page
    Examples:
    |login|password|
    |daria_malakhova|test!2|

  Scenario Outline: Logout
    Given the user enter "<login>" and "<password>"
    And user can find his "<login>" name at the login page
    When user press "logout" button
    Then User can find "login" button
    Examples:
      |login|password|
      |daria_malakhova|test!2|

  Scenario Outline: Track Search
    Given the user enter "<login>" and "<password>"
    And user can find his "<login>" name at the login page
    When user enter track name "<track name>" and press enter
    Then user find <track name> on the page
    Examples:
      |login|password|track name|
      |daria_malakhova|test!2|Tesseract|