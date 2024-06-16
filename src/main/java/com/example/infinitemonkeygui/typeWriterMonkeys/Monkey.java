package com.example.infinitemonkeygui.typeWriterMonkeys;

public class Monkey {

    private final RandomIntegerGenerator randomIntegerGenerator;

    public Monkey() {
        randomIntegerGenerator= new RandomIntegerGenerator();
        Thread monkeyThread = new Thread(randomIntegerGenerator);
        monkeyThread.setName("monkeyThread");
        monkeyThread.start();

    }



}
