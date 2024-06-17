package com.example.infinitemonkeygui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label dataLabel;

    @FXML
    private TextField monkeysAmount;

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
            String userInteger=isValidInteger();

            if(userText==null || userInteger ==null){
                return;
            }
            appController.initData(Integer.parseInt(userInteger), userText);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String isValidText() {
        String text = textToSearch.getText();
        // Regex to match uppercase and lowercase letters, spaces, commas, exclamation marks, question marks, colons, and semicolons
        if (text.isEmpty() || !text.matches("[a-zA-Z\\s,!?;.:]+")) {
            return null;
        }
        return text;
    }


    public String isValidInteger(){
        String text=monkeysAmount.getText();
        if (text.isEmpty() || !text.matches("\\d+")) {
            return null;
        }
        return text;
    }


    public void initData(String data) {
        dataLabel.setText(data);
    }


}
