package helper;

import java.util.Random;

/**
 * Created by omar on 9/3/16.
 */
public class UtilituHelper {
    public static String getRandomNumber(){
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(900000);
        return Integer.toString(n);
    }
}
