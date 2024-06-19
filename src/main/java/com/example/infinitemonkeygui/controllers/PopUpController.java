package com.example.infinitemonkeygui.controllers;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Creates static popUp controllers which can be
 * called from anywhere using the public methods
 */
public class PopUpController {

    /**
     * Creates a popUp for stringMatched
     */
    private static Alert stringMatched;

    
    /**
     * Creates a popUp for stringMatched
     */
    private static Alert wrongInput;

    static {
        createAlerts();
    }

    /**
     * Initialises the Alerts
     */
    private static void createAlerts() {
        Platform.runLater(() -> {
            stringMatched = new Alert(Alert.AlertType.INFORMATION);
            stringMatched.setTitle("String Found");
            stringMatched.setHeaderText(null);
            stringMatched.setContentText("Your String has been found!");

            wrongInput = new Alert(Alert.AlertType.INFORMATION);
            wrongInput.setTitle("Wrong Input Type");
            wrongInput.setHeaderText(null);
            wrongInput.setContentText("Check the input field");
        });
    }

    /**
     * Shows the stringMatched popUp to screen
     */
    public static void stringMatchFoundShowAlert() {
        Platform.runLater(() -> stringMatched.showAndWait());
    }


    /**
     * Shows the wrongInput popUp to screen 
     */
    public static void wrongInputShowAlert() {
        Platform.runLater(() -> wrongInput.showAndWait());
    }
}

