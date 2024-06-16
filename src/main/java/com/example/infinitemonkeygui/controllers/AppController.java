package com.example.infinitemonkeygui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {

    private int monkeyAmount;
    private String textToSearch;

    @FXML
    Label textLabel;



    @FXML
    public void initData(int monkeyAmount, String textToSearch){
        this.monkeyAmount=monkeyAmount;
        this.textToSearch=textToSearch;
        System.out.println(monkeyAmount+ textToSearch);
        textLabel.setText(textToSearch);


    }






}
