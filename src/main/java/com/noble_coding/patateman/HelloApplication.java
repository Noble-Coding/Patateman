package com.noble_coding.patateman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    Stage primaryStage;
    Scene newScene;
    static final int WIDTH = 1000;
    static final int HEIGHT = 800;
    private Floor floor;

    @Override
    public void start(Stage stage) throws IOException {
        Group groupX = new Group();
        primaryStage = stage;
        primaryStage.setTitle("PatateMan!");

        //FOND DE LA SCENE
        newScene = new Scene(groupX, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(newScene);
        deplacementGraphique(newScene);
        primaryStage.show();


    }

    public void deplacementGraphique(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case Z: floor.getFloorTile(1,1).getPlayer(1).deplacement("haut",1); break;
                    case Q: floor.getFloorTile(1,1).getPlayer(1).deplacement("gauche",1); break;
                    case D: floor.getFloorTile(1,1).getPlayer(1).deplacement("droite",1); break;
                    case S: floor.getFloorTile(1,1).getPlayer(1).deplacement("bas",1); break;
                    case A: floor.getFloorTile(1,1).getPlayer(1).PoseBombe(); break;
                }
                //System.out.println(grid.getChildren().toString());
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}