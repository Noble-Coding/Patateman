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

    @Override
    public void start(Stage stage) throws IOException {
        Group groupX = new Group();
        primaryStage = stage;
        primaryStage.setTitle("PatateMan!");

        //FOND DE LA SCENE
        newScene = new Scene(groupX, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(newScene);
        deplacementGraphique(scene,this.grid.getDisplay());
        primaryStage.show();
    }

    public void deplacementGraphique(Scene scene,Group display){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case UP: grid.getCarte().getPlayer(2).deplacement("haut",2); break;
                    case LEFT: grid.getCarte().getPlayer(2).deplacement("gauche",2); break;
                    case RIGHT: grid.getCarte().getPlayer(2).deplacement("droite",2); break;
                    case DOWN: grid.getCarte().getPlayer(2).deplacement("bas",2); break;
                    case M: grid.getCarte().getPlayer(2).PoseBombe(); break;
                    case Z: grid.getCarte().getPlayer(1).deplacement("haut",1); break;
                    case Q: grid.getCarte().getPlayer(1).deplacement("gauche",1); break;
                    case D: grid.getCarte().getPlayer(1).deplacement("droite",1); break;
                    case S: grid.getCarte().getPlayer(1).deplacement("bas",1); break;
                    case A: grid.getCarte().getPlayer(1).PoseBombe(); break;
                }
                //System.out.println(grid.getChildren().toString());
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}