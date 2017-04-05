Feature: MailBoxExtended

  @logOut
  Scenario: Delete draft email by context menu
    Given I create new email and save as draft
      | address                | subject         | body            |
      | someEmail@yandex.ru    | Test_Subject    | BodyTextTest    |
      | someEmail_1@yandex.ru  | Test_Subject_1  | BodyTextTest_1  |
    And I delete email by using context menu
    And I delete email by drag and drop
    Then I can't find deleted emails in the Draft folder
