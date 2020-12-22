package model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

public class StringsHelper {
    public static String generateRandomName(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static Double generateRandomAge(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
