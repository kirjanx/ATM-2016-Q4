package business_object.letter;

import util.RandomNumberUtils;

public class LetterData {

    public static final String ADDRESS = "someEmail@yandex.ru";
    public static final String SUBJECT = "Test_Subject_" + RandomNumberUtils.generateRandomNumber();
    public static final String BODY = "BodyTextTest";
}