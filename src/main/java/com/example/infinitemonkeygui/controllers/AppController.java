package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkey.GlobalVariables;
import com.example.infinitemonkeygui.typeWriterMonkey.Monkey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

public class AppController {

    @FXML
    TextArea randomTextArea, userString, closestString;

    @FXML
    TextField charCountArea;

    @FXML
    Button pauseButton;

    Monkey monkey;

    private boolean paused = false;

    @FXML
    public void initData(String textToSearch){
        GlobalVariables.textToSearch=textToSearch;

        userString.setText(textToSearch);
        userString.setEditable(false);
        closestString.setEditable(false);

        System.out.println(textToSearch);
        monkey = new Monkey(randomTextArea, closestString, charCountArea, pauseButton);
    }

    @FXML
    private void togglePauseResume(ActionEvent event) throws IOException {

        if(GlobalVariables.resetMonkey){
            pauseButton.setText("Reset");
            swapToBeginning(event);
        }

        if (!paused) {
            monkey.pauseMonkey();
            pauseButton.setText("Resume");
        } else {
            monkey.resumeMonkey();
            pauseButton.setText("Pause");
        }
        paused = !paused;
    }

    public void swapToBeginning(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
