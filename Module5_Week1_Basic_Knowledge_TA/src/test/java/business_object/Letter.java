package business_object;

import util.RandomNumberUtils;

public class Letter {

    private static final String EMAIL_ADDRESS = "someEmail@yandex.ru";
    private static final String EMAIL_SUBJECT = "Test_Subject_" + RandomNumberUtils.generateRandomNumber();
    private static final String EMAIL_BODY_TEXT = "BodyTextTest";

    public String getEmailAddress() {
        return EMAIL_ADDRESS;
    }

    public String getEmailSubject() {
        return EMAIL_SUBJECT;
    }

    public String getEmailBodyText() {
        return EMAIL_BODY_TEXT;
    }
}