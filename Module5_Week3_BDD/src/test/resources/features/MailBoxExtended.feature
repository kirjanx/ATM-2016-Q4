Feature: MailBoxExtended

  Scenario: Delete draft email by context menu
    Given I enter MailBox
    And I create new letter with address as "someEmail@yandex.ru", subject as "Test_Subject" and body as "BodyTextTest"
    And I save letter as draft
    When I open draft folder
    Then I can delete letter by using context menu

  Scenario Outline: Delete draft email by drag and drop the letter to the trash
    Given I open MailBox
    And I create new mail with next address "<address>", subject "<subject>" and body "<body>"
    And I save letter as a draft
    When I go to draft folder
    Then I can delete letter by drag and drop

    Examples:
      | address              | subject       | body          |
      | someEmail@yandex.ru  | Test_Subject  | BodyTextTest  |