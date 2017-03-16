Feature: MailBox

  Scenario: Login with valid username and password
    Given I enter valid credentials on home page
    When I click login button
    Then I should see my email address

  Scenario: Create new Email and save as draft
    Given Situated on the Inbox page I click Create New Mail button
    And On the opened page I fill in address as "someEmail@yandex.ru", subject as "Test_Subject" and body as "BodyTextTest"
    And I Click Save button
    When I open Draft folder
    Then I find saved letter with address as "someEmail@yandex.ru" in the Draft folder

  Scenario: Send mail and verify Draft folder is empty
    Given Situated in the Draft folder I open Email
    When I click Send button
    Then I can't find sent letter in Draft folder

  Scenario: Verify that sent mail is presented in Sent folder
    Given I situated in the Draft folder
    When I open Sent folder
    Then I find sent letter with address as "someEmail@yandex.ru" in Sent folder

  Scenario: Clear Sent folder and logout
    Given I clear Sent folder
    When I perform logout
    Then I'm redirecting to the Home page

  Scenario Outline: Create mail, save as draft, open draft mail and verify data in all fields
    Given I log in mailbox
    When I create new email with address as "<address>", subject as "<subject>" and body as "<body>" and save it as draft
    Then I open Draft folder and find my letter with address as "<address>", subject as "<subject>" and body as "<body>"

      Examples:
        | address              | subject       | body          |
        | someEmail@yandex.ru  | Test_Subject  | BodyTextTest  |