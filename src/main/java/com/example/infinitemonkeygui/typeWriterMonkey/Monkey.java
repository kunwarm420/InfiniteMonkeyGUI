package com.example.infinitemonkeygui.typeWriterMonkey;

import java.util.Random;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.example.infinitemonkeygui.controllers.PopUpController;

public class Monkey implements Runnable{

    private int charIndex = 0;
    private int charCount =0;
    private int charCountAreaCount=0;
    private final Random random= new Random();
    private final int stringSize=GlobalVariables.textToSearch.length();

    private final TextField charCountArea;
    private final TextArea outputTextArea, closestString;
    private String closestMatch ="";

    public Monkey(TextArea outputTextArea, TextArea closestString, TextField charCountArea) {
        this.outputTextArea = outputTextArea;
        this.closestString = closestString;
        this.charCountArea= charCountArea;

        Thread monkeyThread = new Thread(this);
        monkeyThread.setName("monkeyThread");
        monkeyThread.start();
    }

    @Override
    public void run() {
        while (true) {
            //Guess random char and add it to the textArea
            Character randomCharacter=guessRandomCharacter();
            updateOutputTextAreaInterface(randomCharacter);
            try {
                doesCharMatch(randomCharacter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateCharCount();
            sleepThread();
        }
    }



    private void doesCharMatch(char randomCharacter) throws IOException {
        char charToMatch=GlobalVariables.textToSearch.charAt(charIndex);
        if(charToMatch==randomCharacter){
            charIndex++;
            checkClosestMatch();
            isStringFound();
        }
        else{
            //reset charIndex to 0
            charIndex=0;
        }
    }

    private void isStringFound() {
        if(charIndex==stringSize){
            PopUpController.stringMatchFoundShowAlert();
        }
    }


    public void checkClosestMatch(){
        if (charIndex > closestMatch.length()){
            closestMatch= GlobalVariables.textToSearch.substring(0, charIndex);
            updateClosestMatchInterface();
        }
    }

    /**
     * Updates the charCount to show how many characters
     * the program has gone through
     * Updates the charCount UI
     * resets the textArea if the count is too high
     */
    private void updateCharCount(){
        charCount++;
        charCountAreaCount++;
        updateCharCountInterface();
        resetTextAreaInterface();
    }

    /**
     *
     */
    private void resetTextAreaInterface(){
        if(charCountAreaCount >= GlobalVariables.maxTextLength){
            Platform.runLater(() -> {
                outputTextArea.setText("");
            });
            charCountAreaCount=0;
        }
    }

    /**
     *
     * @param newChar
     */
    private void updateOutputTextAreaInterface(Character newChar){
        Platform.runLater(() -> {
            outputTextArea.appendText(String.valueOf(newChar));
        });
    }

    /**
     *
     */
    private void updateClosestMatchInterface(){
        Platform.runLater(() -> {
            closestString.setText(closestMatch);
        });
    }

    /**
     * Updates charCountArea UI
     */
    private void updateCharCountInterface(){
       Platform.runLater(() -> {
           charCountArea.setText(String.valueOf(charCount));
       });
   }

    /**
     * Guesses a random number, converts that into character using hashMap
     * @return random Char Character
     */
    private Character guessRandomCharacter(){
        int randomNumber = random.nextInt(1, 58);
        return GlobalVariables.charMap.get(randomNumber);
    }

    /**
     * Sleep to simulate typing delay
     */
    private void sleepThread(){
        try {
            Thread.sleep(GlobalVariables.sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
