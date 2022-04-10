package Bomberman.Components.Enemy;

import Bomberman.Collisions.aTable;
import com.almasb.fxgl.physics.PhysicsWorld;

import static Bomberman.Constants.ConstantCode.ENEMY_SPEED;
import static Bomberman.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Chips3 extends Chips {
    public Chips3() {
        super(-ENEMY_SPEED, 0, 2,4, "enemy3.png");
        PhysicsWorld physics = getPhysicsWorld();

        onCollisionBegin(ENEMY3, BRICK, (enemy3, brick) -> {
            enemy3.getComponent(Chips3.class).turn();
        });
        onCollisionBegin(ENEMY3, WALL, (enemy3, wall) -> {
            enemy3.getComponent(Chips3.class).turn();
        });
        onCollisionBegin(ENEMY3, DOOR, (enemy3, door) -> {
            enemy3.getComponent(Chips3.class).turn();
        });
        onCollisionBegin(ENEMY3, BOMB, (enemy3, bomb) -> {
            enemy3.getComponent(Chips3.class).turn();
        });

        physics.addCollisionHandler(new aTable());
    }

}
