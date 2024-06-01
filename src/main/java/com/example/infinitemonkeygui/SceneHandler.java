package com.example.infinitemonkeygui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneHandler {

    private final Stage stage;
    private final HashMap<Integer, String> allScenes= new HashMap<>();

    public SceneHandler(Stage stage){
        this.stage= stage;
        initScenes();
    }


    public void selectScene(int sceneNumber){
        String fxmlFile= allScenes.get(sceneNumber);

        if(fxmlFile == null){
            System.out.println("Scene Not Found: "+ sceneNumber);
            return;
        }

        try{
            Scene scene= loadScene(fxmlFile);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private Scene loadScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        return(new Scene(fxmlLoader.load()));

    }

    private void initScenes(){
        allScenes.put(1, "fxml/hello-view.fxml");
//        allScenes.put(2, "fxml/choose-level.fxml");
//        allScenes.put(3, "fxml/another-scene.fxml");
    }



}
