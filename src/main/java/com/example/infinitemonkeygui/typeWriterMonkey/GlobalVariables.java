package com.example.infinitemonkeygui.typeWriterMonkey;

import java.util.HashMap;
import java.util.Map;

/**
 * Initialises hashMap
 * Holds global variables
 */
public class GlobalVariables {


    public static String textToSearch;
    public final static int sleepTime= 1;
    public static Map<Integer, Character> charMap;
    public final static int maxTextLength=500;
    public static boolean resetMonkey=false;

    static{
        initHashMap();
    }

    private static void initHashMap() {
        charMap= new HashMap<>();

        // Map uppercase letters
        for (char c = 'A'; c <= 'Z'; c++) {
            charMap.put(c - 'A' + 1, c);
        }

        // Map lowercase letters
        for (char c = 'a'; c <= 'z'; c++) {
            charMap.put(c - 'a' + 27, c);
        }

        // Map specific punctuation characters with unique values
        charMap.put(53, ' ');
        charMap.put(54, ',');
        charMap.put(55, '!');
        charMap.put(56, '?');
        charMap.put(57, ':');
        charMap.put(58, ';');
        charMap.put(58, '.');

        // Print the map for debugging purposes
        for (Map.Entry<Integer, Character> entry : charMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());

        }
    }

}
