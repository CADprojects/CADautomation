package helper;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class RandomizersUtils {

    public static int randomPrefix() {
        return new Random().nextInt(10000) + 1000;
    }

    public static String randomText(int stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }

    public static String randomPassword(int passwordLength) {
        return RandomStringUtils.randomAscii(passwordLength);
    }
}