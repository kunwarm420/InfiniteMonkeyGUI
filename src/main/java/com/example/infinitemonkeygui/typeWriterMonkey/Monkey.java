package com.example.infinitemonkeygui.typeWriterMonkey;

import com.example.infinitemonkeygui.controllers.PopUpController;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

public class Monkey implements Runnable{

    private boolean matchFound;
    private int charIndex = 0;
    private int charCount =0;
    private int charCountAreaCount=0;
    private final Random random= new Random();
    private final int stringSize=GlobalVariables.textToSearch.length();

    private final TextField charCountArea;
    private final TextArea outputTextArea, closestString;
    private final Button toggleButton;
    private String closestMatch ="";

    Thread monkeyThread;
    private boolean paused = false;
    private final Object lock = new Object();

    public Monkey(TextArea outputTextArea, TextArea closestString, TextField charCountArea, Button toggleButton) {
        this.outputTextArea = outputTextArea;
        this.closestString = closestString;
        this.charCountArea= charCountArea;
        this.toggleButton=toggleButton;

        matchFound= false;

        monkeyThread = new Thread(this);
        monkeyThread.setName("monkeyThread");
        monkeyThread.start();
    }

    @Override
    public void run() {
        while (true) {

            synchronized (lock) {
                while (paused) {
                    try {
                        lock.wait(); // Wait until notified to resume
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Guess random char and add it to the textArea
            Character randomCharacter=guessRandomCharacter();
            updateOutputTextAreaInterface(randomCharacter);
            doesCharMatch(randomCharacter);
            updateCharCount();
            if(matchFound){
                GlobalVariables.resetMonkey=true;
                updateButtonInterface();
                return;
            }
            sleepThread();
        }
    }

    private void doesCharMatch(char randomCharacter) {
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
            matchFound= true;
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
     *Updates cloestMatch Ui to show the current closest Match
     */
    private void updateClosestMatchInterface(){
        Platform.runLater(() -> {
            closestString.setText(closestMatch);
        });
    }

    /**
     * Updates charCountArea UI to show the current charCount
     */
    private void updateCharCountInterface(){
       Platform.runLater(() -> {
           charCountArea.setText(String.valueOf(charCount));
       });
   }

    /**
     * Updates button Ui to show "reset"
     */
    private void updateButtonInterface(){
        Platform.runLater(() -> {
            toggleButton.setText("Reset");
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
     * Sleeps to simulate typing delay
     */
    private void sleepThread(){
        try {
            Thread.sleep(GlobalVariables.sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses thread to stop monkey Thread
     */
    public void pauseMonkey() {
        paused = true;
    }

    /**
     * Continues the monkey Thread
     */
    public void resumeMonkey() {
        synchronized (lock) {
            paused = false;
            lock.notify();
        }
    }


}
