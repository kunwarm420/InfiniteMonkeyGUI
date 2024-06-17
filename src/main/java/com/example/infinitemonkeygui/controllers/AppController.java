package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkeys.GlobalVariables;
import com.example.infinitemonkeygui.typeWriterMonkeys.Monkey;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    TextArea randomTextArea, userString, closestString;

    Monkey monkey;

    @FXML
    public void initData(int monkeyAmount, String textToSearch){
        GlobalVariables.typeWriterAmount=monkeyAmount;
        GlobalVariables.textToSearch=textToSearch;

        userString.setText(textToSearch);
        userString.setEditable(false);
        closestString.setEditable(false);

        System.out.println(monkeyAmount + " " +  textToSearch);
        monkey = new Monkey(randomTextArea);
    }

}
