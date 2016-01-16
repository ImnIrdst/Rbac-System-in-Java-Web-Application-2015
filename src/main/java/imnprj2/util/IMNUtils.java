package imnprj2.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by iman on 1/7/16.
 *
 */
public class IMNUtils {
    public static String stringToSHA1(String string){
        return DigestUtils.sha1Hex(string);
    }
}
