package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkeys.GlobalVariables;
import com.example.infinitemonkeygui.typeWriterMonkeys.Monkey;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    Label textLabel;

    @FXML
    TextField randomTextField;

    Monkey monkey;

    @FXML
    public void initData(int monkeyAmount, String textToSearch){
        GlobalVariables.typeWriterAmount=monkeyAmount;
        GlobalVariables.textToSearch=textToSearch;

        textLabel.setText(textToSearch);
        System.out.println(monkeyAmount + " " +  textToSearch);
        monkey = new Monkey();
    }



}
