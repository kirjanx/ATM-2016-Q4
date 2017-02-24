package util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomNumberUtils {

    public static String generateRandomNumber() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}