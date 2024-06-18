package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkey.GlobalVariables;
import com.example.infinitemonkeygui.typeWriterMonkey.Monkey;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AppController {

    @FXML
    TextArea randomTextArea, userString, closestString;
    Monkey monkey;

    @FXML
    public void initData(String textToSearch){
        GlobalVariables.textToSearch=textToSearch;

        userString.setText(textToSearch);
        userString.setEditable(false);
        closestString.setEditable(false);

        System.out.println(textToSearch);
        monkey = new Monkey(randomTextArea, closestString);
    }

}
