package Bomberman.Menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Bomberman.Constants.ConstantCode.*;


public class menuEnJeux extends FXGLMenu {
    public menuEnJeux() {
        super(MenuType.GAME_MENU);
        Shape shape = new Rectangle(SCENE_WIDTH, SCENE_HEIGHT, Color.GREY);
        shape.setOpacity(0.5);

        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/esc_background.png"));
        background.setX(160);
        background.setY(90);
        background.setEffect(new DropShadow(5, 3.5, 3.5, Color.WHITE));
        background.setEffect(new Lighting());



        var menuBox = new VBox(

                new BoutonMenu("Reprendre la partie", 20, () -> fireResume()),
                new BoutonMenu("Allez au score", 20, () -> fireExitToMainMenu()),
                new BoutonMenu("Menu", 20, () -> fireExitToMainMenu()),
                new BoutonMenu("Quitter", 20, () -> fireExit())

        );

        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 110);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 50);
        menuBox.setSpacing(20);

        getContentRoot().getChildren().addAll(shape, background, menuBox);
    }
/*
    private void fireExitToScore() {
        Group groupHighscore;
        Scene sceneHighScorePage;
        Group groupHighScorePage;
        Text highscores;
        HashMap<String, Integer> highScores;
        TableView tableHighScore;
        ArrayList<Map<String, Object>> scores;
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
        dataInHashMap();
        primaryStage.setScene(sceneHighScorePage);

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
    */
}
