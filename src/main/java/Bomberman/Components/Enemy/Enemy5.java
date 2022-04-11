package Bomberman.Components.Enemy;

import Bomberman.Components.bombeComport;
import Bomberman.Components.Joueur;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import static Bomberman.Constants.ConstantCode.ENEMY_SPEED;
import static Bomberman.Constants.ConstantCode.TILED_SIZE;
import static Bomberman.DynamicEntityState.action.DIE;
import static Bomberman.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Enemy5 extends Enemy {
    private boolean isCatching;

    public Enemy5() {
        super(-ENEMY_SPEED, 0, 1, 4, "enemy5.png");
        isCatching = true;

        onCollisionBegin(ENEMY5, BRICK, (enemy5, brick) -> {
            if (speedFactor == 1) {
                enemy5.getComponent(Enemy5.class).turn();
            }
        });
        onCollisionBegin(ENEMY5, WALL, (enemy5, wall) -> {
            enemy5.getComponent(Enemy5.class).turn();
        });
        onCollisionBegin(ENEMY5, DOOR, (enemy5, door) -> {
            enemy5.getComponent(Enemy5.class).turn();
        });
        onCollisionBegin(ENEMY5, BOMB, (enemy5, bomb) -> {
            enemy5.getComponent(Enemy5.class).turn();
        });
        onCollision(ENEMY5, FLAME, (enemy5, flame) -> {
            if(flame.getComponent(bombeComport.class).isActivation()) {
                enemy5.getComponent(Enemy5.class).setStateDie();
                getGameTimer().runOnceAfter(() -> {
                    enemy5.removeFromWorld();
                    set("enemies", getGameWorld().getGroup(ENEMY1,
                            ENEMY2, ENEMY3, ENEMY4, ENEMY5).getSize());
                }, Duration.seconds(2.4));
            }
        });

    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);

        Entity player = getGameWorld().getSingleton(PLAYER);

        if (state == DIE || player
                .getComponent(Joueur.class)
                .getState() == DIE) {
            return;
        }

        int playerCellX = (int) (player.getX() / TILED_SIZE);
        int playerCellY = (int) (player.getY() / TILED_SIZE);
        int enemyCellY = (int) (entity.getY() / TILED_SIZE);
        int enemyCellX = (int) (entity.getX() / TILED_SIZE);
        if (getEntity().distance(player) < TILED_SIZE * 5) {
            if (isCatching == true) {
                if (dx == 0) {
                    if ((entity.getY() - player.getY()) * dy < 0) {
                        speedFactor = 1.5;
                    } else {
                        speedFactor = 1;
                    }

                    if (enemyCellY == playerCellY) {
                        if (player.getX() > entity.getX()) {
                            turnRight();
                        } else {
                            turnLeft();
                        }
                    }
                } else if (dy == 0) {
                    if ((entity.getX() - player.getX()) * dx < 0) {
                        speedFactor = 1.5;
                    } else {
                        speedFactor = 1;
                    }

                    if (enemyCellX == playerCellX) {
                        if (player.getY() > entity.getY()) {
                            turnDown();
                        } else {
                            turnUp();
                        }
                    }
                }
            } else if (dx == 0 && ((int) entity.getY() % TILED_SIZE <= 5 && (int) entity.getY() % TILED_SIZE > 0)) {
                isCatching = true;
            } else if (dy == 0 && ((int) entity.getX() % TILED_SIZE <= 5 && (int) entity.getY() % TILED_SIZE > 0)) {
                isCatching = true;
            }
        } else {
            speedFactor = 1;
            isCatching = true;
        }

    }

    @Override
    public void turn() {
        isCatching = false;
        super.turn();
    }
}
