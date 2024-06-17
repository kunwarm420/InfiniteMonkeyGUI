package com.example.infinitemonkeygui.typeWriterMonkeys;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.LinkedList;
import java.util.Random;

public class Monkey implements Runnable{

    private int charIndex = 0;
    private final Random random= new Random();
    private final int stringSize=GlobalVariables.textToSearch.length();

    public static LinkedList<Character> buffer;
    private final TextArea outputTextArea;

    public Monkey(TextArea outputTextField) {
        buffer = new LinkedList<>();
        this.outputTextArea = outputTextField;

        Thread monkeyThread = new Thread(this);
        monkeyThread.setName("monkeyThread");
        monkeyThread.start();
        System.out.println("String Size: " + stringSize);
    }

    @Override
    public void run() {
        while (true) {
            int randomNumber = random.nextInt(1, 58);
            Character character= GlobalVariables.charMap.get(randomNumber);

            // Update the TextField on the JavaFX Application Thread
            Platform.runLater(() -> {
                outputTextArea.appendText(character.toString());
            });

            try {
                matchString(character);
            } catch (StringFoundException e) {
               return;
            }

            sleepThread();
        }
    }



    public void matchString(Character myChar) throws StringFoundException {
//      System.out.println("Checking: " + GlobalVariables.textToSearch.charAt(charIndex) +  myChar);
        if(GlobalVariables.textToSearch.charAt(charIndex)==myChar){
            charIndex++;
            if(charIndex==stringSize){
                System.out.println("Matched");
                throw new StringFoundException("String Found");
            }
        }else{
            charIndex=0;
        }
    }

    /**
     * Sleep to simulate typing delay
     */
    public void sleepThread(){
        try {
            Thread.sleep(GlobalVariables.sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
