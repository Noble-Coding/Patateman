package Bomberman.Components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static Bomberman.Constants.ConstantCode.TILED_SIZE;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class bricCasse extends Component {
    AnimatedTexture texture;
    AnimationChannel animation;

    public bricCasse() {
        animation = new AnimationChannel(image("brick_break.png"), 3, TILED_SIZE, TILED_SIZE,
                Duration.seconds(0.4), 0, 2);
        texture = new AnimatedTexture(animation);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }
}
