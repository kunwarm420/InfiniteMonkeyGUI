package com.example.infinitemonkeygui.typeWriterMonkeys;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Monkey implements Runnable{

    private final int charIndex = 0;
    private final Random random= new Random();
    public  static LinkedList<Character> buffer;


    public Monkey() {
        buffer = new LinkedList<>();

        Thread monkeyThread = new Thread(this);
        monkeyThread.setName("monkeyThread");
        monkeyThread.start();
    }

    @Override
    public void run() {
        while (true) {
            int randomNumber = random.nextInt(1, 58);
            Character character= charMap.get(randomNumber);
            System.out.println(character);
        }
    }

}
