package org.devgateway.geoph.util;

import java.util.Random;

/**
 * @author dbianco
 *         created on jun 09 2016.
 */
public class KeyGenerator {

    public static final String ALPHABET = "BCDFGHIJKLMNPQRSTVWXZ";
    public static final int ALPHABET_NUMBER = ALPHABET.length();

    public static String getRandomKey(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET_NUMBER)));
        }
        return sb.toString().toLowerCase();
    }
}
