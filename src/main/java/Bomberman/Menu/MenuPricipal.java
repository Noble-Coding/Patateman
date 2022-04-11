package Bomberman.Menu;

import Bomberman.UI.seinededépart;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.input.view.KeyView;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

public class MenuPricipal extends FXGLMenu {
    public MenuPricipal() {
        super(MenuType.MAIN_MENU);
        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/main_background.png"));

        var menuBox = new VBox(
                new BoutonMenu("Nouvelle partie", 27, () -> newGame()),
                new BoutonMenu("Contrôles", 27, () -> instruct()),
                new BoutonMenu("Quitter", 27, () -> fireExit())
        );

        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() * 0.35);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 60);
        menuBox.setSpacing(20);

        getContentRoot().getChildren().addAll(background, menuBox);
    }

    private void instruct() {
        GridPane pane = new GridPane();

        pane.addRow(0, getUIFactoryService().newText(" Mouvement      "),
                new HBox(new KeyView(Z), new KeyView(S), new KeyView(A), new KeyView(D)));
        pane.addRow(1, getUIFactoryService().newText(" Placer une bombe      "),
                new KeyView(SPACE));

        getDialogService().showBox("Comment jouer", pane, getUIFactoryService().newButton("OK"));
    }


    public void newGame() {
        fireNewGame();
        getGameTimer().runOnceAfter(() -> {
            getSceneService().pushSubScene(new seinededépart());
        }, Duration.millis(10));
    }
}
