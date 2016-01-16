package imnprj2.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by iman on 1/7/16.
 */
public class IMNUtilsTest {

    @Test
    public void testStringToSHA1() throws Exception {
        System.out.println(IMNUtils.stringToSHA1("Hello"));
        System.out.println(IMNUtils.stringToSHA1("Hello"));
    }
}