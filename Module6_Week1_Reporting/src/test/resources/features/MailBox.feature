Feature: MailBox

  Scenario: Login with valid username and password
    Given I enter valid credentials on home page
    When I click login button
    Then I should see my email address

  Scenario: Create new Email and save as draft
    Given I click Create New Mail button on Inbox page
    When I fill in address: "someEmail@yandex.ru"; subject: "Test_Subject"; body: "BodyTextTest"; click Save button on the new mail page
    Then I find saved letter with address: "someEmail@yandex.ru"; in the Draft folder

  Scenario: Send mail and verify Draft folder is empty
    Given I open Email in the Draft folder
    When I click Send button
    Then I can't find sent letter in Draft folder

  Scenario: Verify that sent mail is presented in Sent folder
    Given I'm in the Draft folder
    When I open Sent folder
    Then I find sent letter with address: "someEmail@yandex.ru"; in Sent folder

  Scenario: Clear Sent folder and logout
    Given I clear Sent folder
    When I perform logout
    Then I'm redirecting to the Home page

  @clearDraft
  Scenario: Create mail, save as draft, open draft mail and verify data in all fields
    Given I create new email and save draft
      | address              | subject       | body          |
      | someEmail@yandex.ru  | Test_Subject  | BodyTextTest  |
    When I open Draft folder
    Then I find created letter with valid data in the Draft folder