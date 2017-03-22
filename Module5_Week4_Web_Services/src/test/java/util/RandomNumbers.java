package util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomNumbers {

    public static int generateRandomNumber() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(5));
    }
}