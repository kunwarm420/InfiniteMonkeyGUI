package com.example.infinitemonkeygui.typeWriterMonkey;

import com.example.infinitemonkeygui.controllers.PopUpController;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

/**
 * Backend aka the monkey typing and the displaying
 * of the data in the screen
 */
public class Monkey implements Runnable{

    private boolean matchFound;
    private int charIndex = 0;
    private int charCount =0;

    /**
     * How many chars are currently displayed on outputArea
     * to manage when to clear due to memory issue as textarea uses heapMemory
     */
    private int charCountAreaCount=0;

    /**
     * To get random int which is converted into
     * chars letters by mapping int against hashMap
     */
    private final Random random= new Random();

    /**
     * Size of the text To Search for.
     * Ensures it doesnt need to be checked everytime
     */
    private final int stringSize=GlobalVariables.textToSearch.length();

    /**
     * User Interfaces
     */
    private final TextField charCountArea;

    /**
     * User Interfaces
     */
    private final TextArea outputTextArea, closestString;

    /**
     * User Interfaces
     */
    private final Button toggleButton;
    private String closestMatch ="";

    /**
     * Thread used to run operations of this class
     */
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

    /**
     * Checks if the given {@code randomCharacter} matches the
     * expected character from the {@code GlobalVariables.textToSearch}.
     *
     * @param randomCharacter new character found
     */
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

    /**
     * When a Char is matched, this is called
     * to check if we have found the complete string
     */
    private void isStringFound() {
        if(charIndex==stringSize){
            matchFound= true;
            PopUpController.stringMatchFoundShowAlert();
        }
    }


    /**
     * When matching char is found
     * checks if this is the longest matching char
     * if true, makes this the new longest matching string
     * and updates the user interface in the application
     */
    public void checkClosestMatch(){
        if (charIndex > closestMatch.length()){
            closestMatch= GlobalVariables.textToSearch.substring(0, charIndex);
            Platform.runLater(() -> {
                closestString.setText(closestMatch);
            });
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

        //Updates the userInterface charCountArea
        Platform.runLater(() -> {
            charCountArea.setText(String.valueOf(charCount));
        });


        resetTextAreaInterface();
    }

    /**
     * Checks if the total Char amount in the textArea
     * is higher than allowed.
     * If true, textArea is reset
     * and @param charCountAreaCount is set to 0.
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
     * Updates the outputTextArea UI by adding the new char to it
     * @param newChar char given by random
     */
    private void updateOutputTextAreaInterface(Character newChar){
        Platform.runLater(() -> {
            outputTextArea.appendText(String.valueOf(newChar));
        });
    }


    /**
     * Updates charCountArea UI to show the current charCount
     */

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
