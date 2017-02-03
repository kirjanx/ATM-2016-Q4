package util;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {

    public static String generateRandomSubjectTitle(){
        return "TestSubject_" + RandomStringUtils.randomAlphanumeric(5);
    }

    public static String generateRandomSubjectTitle2(){
        return "TestSubject_" + RandomStringUtils.randomAlphanumeric(5);
    }

    public static final String SUBJECT = Util.generateRandomSubjectTitle();
    //
    public static final String SUBJECT_2 = Util.generateRandomSubjectTitle2();

}
