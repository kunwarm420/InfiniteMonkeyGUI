package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Scene2Controller {

    @FXML
    private void switchToScene1(ActionEvent event) {
        // Logic to switch to Scene 1
        SceneManager.setScene("/Scene1.fxml");
    }

}
