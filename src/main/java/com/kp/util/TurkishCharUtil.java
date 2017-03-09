package com.kp.util;

import java.text.Normalizer;

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
}
