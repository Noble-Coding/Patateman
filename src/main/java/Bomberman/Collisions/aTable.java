package Bomberman.Collisions;

import Bomberman.Components.Enemy.Chips1;
import Bomberman.Components.Enemy.Chips3;
import Bomberman.Components.aperoRecette;
import Bomberman.GameType;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.util.Duration;
import static Bomberman.Constants.ConstantCode.ENEMY_SPEED;
import static Bomberman.DynamicEntityState.action.*;
import static Bomberman.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class aTable extends CollisionHandler {
    public aTable() {
        super(GameType.FLAME, GameType.ENEMY3);
    }

    @Override
    protected void onCollision(Entity flame, Entity enemy) {
        if (flame.getComponent(aperoRecette.class).isActivation()
                && enemy.getComponent(Chips3.class).getState() != DIE) {
            enemy.getComponent(Chips3.class).setStateDie();
            getGameTimer().runOnceAfter(() -> {
                onTransform(enemy);
                enemy.removeFromWorld();
                set("enemies", getGameWorld().getGroup(ENEMY1,
                        ENEMY2, ENEMY3, ENEMY4, ENEMY5).getSize());
            }, Duration.seconds(2.4));
        }
    }

    private void onTransform(Entity parent) {
        Entity child1 = spawn("enemy1", new SpawnData(parent.getX(), parent.getY()));
        Entity child2 = spawn("enemy1", new SpawnData(parent.getX(), parent.getY()));

        if (parent.getComponent(Chips3.class).getDx() == 0) {
            child1.getComponent(Chips1.class).setState(UP);
            child1.getComponent(Chips1.class).setDxDy(0, -ENEMY_SPEED);
            child2.getComponent(Chips1.class).setState(DOWN);
            child2.getComponent(Chips1.class).setDxDy(0, ENEMY_SPEED);
        } else if (parent.getComponent(Chips3.class).getDy() == 0) {
            child1.getComponent(Chips1.class).setState(LEFT);
            child1.getComponent(Chips1.class).setDxDy(-ENEMY_SPEED, 0);
            child2.getComponent(Chips1.class).setState(RIGHT);
            child2.getComponent(Chips1.class).setDxDy(ENEMY_SPEED, 0);
        }
    }
}
