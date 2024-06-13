package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Scene1Controller {

    @FXML
    private void switchToScene2(ActionEvent event) {
        // Logic to switch to Scene 2
        SceneManager.setScene("/Scene2.fxml");
    }

}
