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

import static Bomberman.Constants.ConstantCode.*;


public class RepriseMenu extends FXGLMenu {
    public RepriseMenu() {
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

                new ChoisirSonPlat("Rassieds-toi", 20, () -> fireResume()),
                new ChoisirSonPlat("Reprendre une assiette", 20, () -> fireExitToMainMenu()),
                new ChoisirSonPlat("Se lever de table", 20, () -> fireExit())
        );

        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 110);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 50);
        menuBox.setSpacing(20);

        getContentRoot().getChildren().addAll(shape, background, menuBox);
    }

}
