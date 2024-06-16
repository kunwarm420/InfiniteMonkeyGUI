package com.example.infinitemonkeygui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InfiniteMonkey extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("FXML Scene Switching Example");
        stage.setResizable(false);
        stage.show();

//        SceneManager.setPrimaryStage(stage);


    }


    public static void main(String[] args) {
        launch();
    }
}
