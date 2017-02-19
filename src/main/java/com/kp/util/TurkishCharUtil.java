package com.kp.util;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcan on 20/02/17.
 */
public class TurkishCharUtil {

    private TurkishCharUtil() {
    }

    public static String replace(String value) {
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.replaceAll("\\p{M}", "");
        return value;
    }

    private static Map<Character, Character> turkishChars() {
        Map<Character, Character> turkishChars = new HashMap();
        turkishChars.put('Ç', 'C');
        turkishChars.put('ç', 'c');
        turkishChars.put('İ', 'I');
        turkishChars.put('ı', 'i');
        turkishChars.put('Ü', 'U');
        turkishChars.put('ü', 'u');
        turkishChars.put('Ğ', 'G');
        turkishChars.put('ğ', 'g');
        turkishChars.put('Ş', 'S');
        turkishChars.put('ş', 's');

        return turkishChars;
    }
}
