package com.example.infinitemonkeygui;

import javafx.application.Application;
import javafx.stage.Stage;

public class InfiniteMonkey extends Application {

    private SceneHandler sceneHandler;

    @Override
    public void start(Stage stage) throws Exception {

        sceneHandler = new SceneHandler(stage);
        sceneHandler.selectScene(1);
        stage.setTitle("Scene Switcher");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
