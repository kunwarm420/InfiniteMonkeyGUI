package com.example.infinitemonkeygui.typeWriterMonkey;

import com.example.infinitemonkeygui.controllers.PopUpController;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Monkey implements Runnable{

    private int charIndex = 0;
    private int charCount =0;
    private int charCountAreaCount=0;
    private final Random random= new Random();
    private final int stringSize=GlobalVariables.textToSearch.length();

    public static LinkedList<Character> buffer;
    private final TextArea outputTextArea, closestString;
    private final TextField charCountArea;

    private String closestMatch;

    public Monkey(TextArea outputTextField, TextArea closestString, TextField charCountArea) {
        buffer = new LinkedList<>();
        this.outputTextArea = outputTextField;
        this.closestString = closestString;
        this.charCountArea= charCountArea;

        closestMatch="";

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

            //Update Count

            updateCharCount();
            sleepThread();
        }
    }


    private void updateCharCount(){
        charCount++;
        charCountAreaCount++;

        Platform.runLater(() -> {
            charCountArea.setText(String.valueOf(charCount));
        });

        if(charCountAreaCount >= GlobalVariables.maxTextLength){
            Platform.runLater(() -> {
                outputTextArea.setText("");
            });
            charCountAreaCount=0;
        }

    }


    public void matchString(Character myChar) throws StringFoundException {

        if(GlobalVariables.textToSearch.charAt(charIndex)==myChar){
            System.out.println("Matched: " + GlobalVariables.textToSearch.charAt(charIndex) + " " + myChar);
            charIndex++;
            checkClosestMatch();

            if(charIndex==stringSize){
                try {
                    PopUpController.stringMatchShowAlert();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Matched");
                throw new StringFoundException("String Found");
            }
        }else{
            charIndex=0;
        }
    }

    public void checkClosestMatch(){

        System.out.println("hello"  + closestMatch.length() + charIndex);
        if (charIndex > closestMatch.length()){
            closestMatch= GlobalVariables.textToSearch.substring(0, charIndex);
            Platform.runLater(() -> {
                closestString.setText(closestMatch);
            });
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
