package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkeys.Monkey;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {

    private int monkeyAmount;
    private String textToSearch;

    @FXML
    Label textLabel;

    Monkey monkey;

    @FXML
    public void initData(int monkeyAmount, String textToSearch){
        this.monkeyAmount=monkeyAmount;
        this.textToSearch=textToSearch;
        textLabel.setText(textToSearch);
        System.out.println(monkeyAmount+ textToSearch);
        monkey= new Monkey();
    }



}
