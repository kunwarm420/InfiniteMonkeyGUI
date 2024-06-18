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

    @FXML
    private void switchToApp(ActionEvent event) {
        // Logic to switch to Scene 2
        // SceneManager.setScene("/menu.fxml");
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
