package com.example.infinitemonkeygui.controllers;

import com.example.infinitemonkeygui.typeWriterMonkey.GlobalVariables;
import com.example.infinitemonkeygui.typeWriterMonkey.Monkey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    @FXML
    TextArea randomTextArea, userString, closestString;

    @FXML
    TextField charCountArea;

    @FXML
    Button pauseButton;

    Monkey monkey;

    private boolean paused = false;

    /**
     * Initializes the global Variables 
     * @param textToSearch
     */
    @FXML
    public void initData(String textToSearch){
        GlobalVariables.textToSearch=textToSearch;

        userString.setText(textToSearch);
        userString.setEditable(false);
        closestString.setEditable(false);

        System.out.println(textToSearch);
        monkey = new Monkey(randomTextArea, closestString, charCountArea, pauseButton);
    }

    /**
     * Toggles button to allow for pause and resume monkey typing
     * @param event
     * @throws IOException
     */
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

    /**
     * Resets the monkey to the beginning of the text area
     * when user given text is found
     * @param event
     * @throws IOException
     */
    public void swapToBeginning(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
