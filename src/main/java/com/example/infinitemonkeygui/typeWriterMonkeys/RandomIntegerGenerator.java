package com.example.infinitemonkeygui.typeWriterMonkeys;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomIntegerGenerator implements Runnable {

    private final Random random;
    public static Map<Integer, Character> charMap;

    public RandomIntegerGenerator(){
        random= new Random();
        initHashMap();
    }

    @Override
    public void run() {
        while (true) {
            int randomNumber = random.nextInt(1, 58);
            Character character= charMap.get(randomNumber);
        }
    }


    private void initHashMap() {
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