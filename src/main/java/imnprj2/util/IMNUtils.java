package imnprj2.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by iman on 1/7/16.
 *
 */
public class IMNUtils {
    public static String stringToSHA1(String string){
        return DigestUtils.sha1Hex(string);
    }

    public static boolean isSameDay(Timestamp timestamp1, Timestamp timestamp2) {
        String[] date1 = timestamp1.toString().split(" ");
        String[] data2 = timestamp2.toString().split(" ");
        return date1[0].equals(data2[0]);
    }
}
