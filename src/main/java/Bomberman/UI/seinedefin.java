package Bomberman.UI;

import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Point2D;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static Bomberman.Constants.ConstantCode.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class seinedefin extends SubScene {
    public seinedefin(String title) {

        var background = new Rectangle(SCENE_WIDTH, SCENE_HEIGHT, Color.color(0, 0, 0, 1));

        var titleText = getUIFactoryService().newText(title, Color.WHITE, 40);
        titleText.setStroke(Color.WHITESMOKE);
        titleText.setStrokeWidth(1.5);
        titleText.setEffect(new Bloom(0.6));
        titleText.setX(SCENE_WIDTH / 6);
        titleText.setY(SCENE_HEIGHT / 3);
        getContentRoot().getChildren().addAll(background, titleText);

        animationBuilder()
                .onFinished(() -> {
                    animationBuilder()
                            .onFinished(() -> popSubScene())
                            .duration(Duration.seconds(7))
                            .scale(titleText)
                            .from(new Point2D(1.1,1.1))
                            .to(new Point2D(1,1))
                            .buildAndPlay(this);
                })
                .duration(Duration.seconds(7))
                .scale(titleText)
                .from(new Point2D(1,1))
                .to(new Point2D(1.1,1.1))
                .buildAndPlay(this);
    }

    public void popSubScene() {
        getSceneService().popSubScene();
        getGameController().gotoMainMenu();
    }
}
