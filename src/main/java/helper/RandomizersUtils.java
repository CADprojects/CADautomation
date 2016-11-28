package helper;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */

import java.util.Random;

public class RandomizersUtils {

    public static int random_prefix() {
        return new Random().nextInt(10000) + 1000;
    }
}
