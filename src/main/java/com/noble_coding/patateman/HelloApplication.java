package com.noble_coding.patateman;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {
    //déclaration des variables
    static final int WIDTH = 1920;
    static final int HEIGHT = 1000;
    static int scoreint = 0;
    Text score;
    Button buttonPauseSceneGame;
    Group groupMenu;
    Group groupHighscore;
    Scene sceneGameInitial;
    List<Scene> listScenesGame;
    int indexActiveSceneGame = 0;
    List<Group> listGroupGame;
    Group groupGameInitial;
    VBox vboxMenu;
    VBox vboxHighscore;
    Stage primaryStage;
    Scene sceneMenu;
    boolean gamePaused = true;
    Button buttonGoToHighScorePage;
    Scene sceneHighScorePage;
    Group groupHighScorePage;
    Button buttonStartMainMenu;
    Button buttonReturnToMenu;
    Button buttonReturnToGameFromPause;
    Text highscores;
    TextField saisiePseudo;
    String pseudoPlayer;
    //JSONObject highScoreData;
    ArrayList<Map<String, Object>> itemsScore;
    Timer timerCerise = new Timer();
    HashMap<String, Integer> highScores;
    TableView tableHighScore;
    ArrayList<Map<String, Object>> scores;



    @Override
    public void start(Stage stage) throws IOException {
        listScenesGame = new ArrayList<Scene>();
        listGroupGame = new ArrayList<Group>();
        primaryStage = stage;
        primaryStage.setTitle("Patateman");
        //SCENE MENU DEFINITION

        groupMenu = new Group();
        groupHighscore = new Group();
        buttonStartMainMenu = new Button("Démarrer le jeu");
       // buttonStartMainMenu.setVisible(true);
        buttonGoToHighScorePage = new Button("Aller au score!");
        buttonReturnToGameFromPause = new Button("Retourner au jeu");
        saisiePseudo = new TextField();

        saisiePseudo.setId("textField");
        saisiePseudo.setPromptText("Entrer votre pseudo");

       // groupMenu.setLayoutX(WIDTH/2);
        // groupMenu.setLayoutY(HEIGHT/2);
        vboxMenu = new VBox(saisiePseudo,buttonStartMainMenu);
        vboxHighscore = new VBox(buttonGoToHighScorePage);
        vboxMenu.setSpacing(10);
        vboxMenu.setPadding(new Insets(640,20, 10,875));
        vboxHighscore.setSpacing(10);
        vboxHighscore.setPadding(new Insets(20,20, 10,1800));

        groupMenu.getChildren().add(vboxMenu);
        groupMenu.getChildren().add(vboxHighscore);

       /* Condition pour faire apparaitre le button demarre le jeu si on rentre un pseudo
        if ( (saisiePseudo.getText() == null) || (saisiePseudo.getText().length() == 0)) {

            buttonStartMainMenu.setVisible(false);
        }
        else buttonStartMainMenu.setVisible(true);
        */

        sceneMenu = new Scene(groupMenu, WIDTH, HEIGHT, Color.BLACK);
        //Ajout d'une image en background du menu
        Image imPatateman = new Image(new File("C:\\Users\\leoma\\Desktop\\Patateman\\Patateman\\src\\main\\java\\com\\noble_coding\\patateman\\assets\\images\\Patateman.png").toURI().toURL().toExternalForm());
        sceneMenu.setFill(new ImagePattern(imPatateman));
        primaryStage.setScene(sceneMenu);
        primaryStage.show();

        //END SCENE MENU DEFINITION

        //Page de highscore
        buttonReturnToMenu = new Button("Return to menu!");
        highscores = new Text();
        highscores.setX(200);
        highscores.setY(200);
        highscores.setFill(Color.WHITE);
        groupHighScorePage = new Group();
        groupHighScorePage.getChildren().add(buttonReturnToMenu);
        groupHighScorePage.getChildren().add(highscores);
        tableHighScore = new TableView();
        scores = new ArrayList<Map<String, Object>>();

        TableColumn<Map, String> col1 = new TableColumn<>("userName");
        col1.setCellValueFactory(new MapValueFactory<>("userName"));

        TableColumn<Map, String> col2 = new TableColumn<>("score");
        col2.setCellValueFactory(new MapValueFactory<>("score"));

        scores.addAll(getInfoFromFile());

        for (Map<String, Object> item:scores) {
            tableHighScore.getItems().addAll(item);
        }

        tableHighScore.getColumns().add(col1);
        tableHighScore.getColumns().add(col2);

        tableHighScore.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      
        tableHighScore.setLayoutX(WIDTH/4);
        tableHighScore.setLayoutY(HEIGHT/4);
        groupHighScorePage.getChildren().add(tableHighScore);

        sceneHighScorePage = new Scene(groupHighScorePage, WIDTH, HEIGHT, Color.BLACK);
       // sceneHighScorePage.setFill(new ImagePattern(imLaurier));


        primaryStage.show();

        buttonStartMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                primaryStage.setScene(listScenesGame.get(indexActiveSceneGame));
                gamePaused = false;
               // tl.play();
            }
        });

        buttonReturnToGameFromPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(listScenesGame.get(indexActiveSceneGame));
                gamePaused = false;
               // tl.play();
                vboxMenu.getChildren().remove(buttonReturnToGameFromPause);
            }
        });

        buttonGoToHighScorePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataInHashMap();
                primaryStage.setScene(sceneHighScorePage);
                gamePaused = true;
               // tl.pause();
            }
        });

        buttonReturnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(sceneMenu);
                gamePaused = true;
               // tl.pause();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }





    private void run() {

    }

    public ArrayList<Map<String, Object>> getInfoFromFile()  {

        Map<String, Object> item1 = new HashMap<>();
        item1.put("userName", "test1");
        item1.put("score", 2000);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("userName", "test2");
        item2.put("score", 10000);

        ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        items.add(item1);
        items.add(item2);

        return items;
    }
    public void dataInHashMap(){
        highScores = new HashMap<String, Integer>();
        highScores.put("Joueur1", 50);
        highScores.put("Joueur2", 40);
        highScores.put("Joueur3", 60);

    }
    }