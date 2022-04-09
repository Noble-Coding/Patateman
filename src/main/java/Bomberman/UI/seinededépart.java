package Bomberman.UI;

import com.almasb.fxgl.scene.SubScene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static Bomberman.Constants.ConstantCode.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class seinededépart extends SubScene {
    public seinededépart() {

        var background = new Rectangle(SCENE_WIDTH, SCENE_HEIGHT, Color.color(0, 0, 0, 1));

        var title = getUIFactoryService().newText("Stage " + geti("level"), Color.WHITE, 40);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);
        title.setEffect(new Bloom(0.6));
        title.setX(SCENE_WIDTH / 3);
        title.setY(SCENE_HEIGHT / 2);
        getContentRoot().getChildren().addAll(background, title);

        animationBuilder()
                .onFinished(() -> popSubScene())
                .duration(Duration.seconds(4))
                .fade(getContentRoot())
                .from(1)
                .to(1)
                .buildAndPlay(this);
    }

    public void popSubScene() {
        getSceneService().popSubScene();
    }

}
