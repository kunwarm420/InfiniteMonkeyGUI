package com.example.infinitemonkeygui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    TextArea textToSearch;

    /**
     * Switches to the app scene {app.fxml}
     * @param event users clicks on submit button of MenuController
     */
    @FXML
    private void switchToApp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app.fxml"));
            Parent root = loader.load();
            AppController appController = loader.getController();

            String userText=isValidText();
            if(userText==null){
                return;
            }
            appController.initData(userText);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if the user input is valid
     * @return null if invalid text, else user string 
     * @throws IOException
     */
    public String isValidText() throws IOException {
        String text = textToSearch.getText();
        // Regex to match uppercase and lowercase letters, spaces, commas, exclamation marks, question marks, colons, and semicolons
        if (text.isEmpty() || !text.matches("[a-zA-Z\\s,!?;.:]+")) {

            PopUpController.wrongInputShowAlert();
            return null;
        }
        return text;
    }


}
