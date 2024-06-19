package com.example.infinitemonkeygui.typeWriterMonkey;

import java.util.HashMap;
import java.util.Map;

/**
 * Initialises hashMap
 * Holds global variables
 */
public class GlobalVariables {


    /**
     * Text given by the user to search for
     */
    public static String textToSearch;

    /**
     * Delay to simulate typing 
     */
    public final static int sleepTime= 1;

    /**
     * static hashMap so it can be accessed by all classes
     * key is the index of the character in the textToSearch
     * value is the character that should be typed
     */
    public static Map<Integer, Character> charMap;

    /*
     * Maximum amount of char to be displayed
     * to ensure RAM usage is low
     */
    public final static int maxTextLength=500;


    /**
     * Allows for button on appScene to be changed to 
     * reset when user String is Found
     */
    public static boolean resetMonkey=false;

    static{
        initHashMap();
    }

    /*
     * Initialises hashMap
     */
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
