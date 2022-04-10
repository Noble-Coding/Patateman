package Bomberman;

import Bomberman.Components.Enemy.*;
import Bomberman.Components.aperoRecette;
import Bomberman.Menu.RepriseMenu;
import Bomberman.Menu.GrosMenu;
import Bomberman.UI.tableplatdessert;
import Bomberman.UI.tableplatentree;
import Bomberman.UI.Templates;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsWorld;
import Bomberman.Components.Fermier;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

import static Bomberman.Components.Fermier.*;
import static Bomberman.Constants.ConstantCode.*;
import static Bomberman.DynamicEntityState.action.*;
import static Bomberman.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;


public class GameApp extends GameApplication {
    private Map temp = new HashMap();
    private boolean isLoading = false;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(SCENE_WIDTH);
        gameSettings.setHeight(SCENE_HEIGHT);
        gameSettings.setFullScreenAllowed(true);
        gameSettings.setFullScreenFromStart(true);

        gameSettings.setIntroEnabled(false);
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setFontUI("game_font.ttf");
        gameSettings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new GrosMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new RepriseMenu();
            }

        });
//        gameSettings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameFactory());
        nextLevel();
        spawn("background");
    }

    private Entity getPlayer() {
        return getGameWorld().getSingleton(PLAYER);
    }

    private Fermier getPlayerComponent() {
        return getPlayer().getComponent(Fermier.class);
    }


    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                getPlayerComponent().up();
            }

            @Override
            protected void onActionEnd() {
                getPlayerComponent().stop();
            }
        }, KeyCode.Z);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                getPlayerComponent().down();
            }

            @Override
            protected void onActionEnd() {
                getPlayerComponent().stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getPlayerComponent().left();
            }

            @Override
            protected void onActionEnd() {
                getPlayerComponent().stop();
            }
        }, KeyCode.Q);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                getPlayerComponent().right();
            }

            @Override
            protected void onActionEnd() {
                getPlayerComponent().stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Place Bomb") {
            @Override
            protected void onActionBegin() {
                getPlayerComponent().placeBomb(geti("flame"));
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physics = getPhysicsWorld();
        physics.setGravity(0, 0);

        onCollisionBegin(PLAYER, DOOR, (player, door) -> {
            if (isLoading == false
                    && getGameWorld().getGroup(ENEMY1, ENEMY2,
                    ENEMY3, ENEMY4, ENEMY5).getSize() == 0) {
                isLoading = true;
                getPlayerComponent().setBombInvalidation(true);
                play("next_level.wav");
                getGameTimer().runOnceAfter(() -> {
                    nextLevel();
                }, Duration.seconds(4));
            }
        });

        onCollisionBegin(PLAYER, ENEMY1, (player, enemy) -> {
            if (enemy.getComponent(Chips1.class).getState() != DIE
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
        onCollisionBegin(PLAYER, ENEMY2, (player, enemy) -> {
            if (enemy.getComponent(Chips2.class).getState() != DIE
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
        onCollisionBegin(PLAYER, ENEMY3, (player, enemy) -> {
            if (enemy.getComponent(Chips3.class).getState() != DIE
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
        onCollisionBegin(PLAYER, ENEMY4, (player, enemy) -> {
            if (enemy.getComponent(Chips4.class).getState() != DIE
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
        onCollisionBegin(PLAYER, ENEMY5, (player, enemy) -> {
            if (enemy.getComponent(Chips5.class).getState() != DIE
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
        onCollision(PLAYER, FLAME, (player, flame) -> {
            if (flame.getComponent(aperoRecette.class).isActivation()
                    && getPlayerComponent().getPlayerSkin() == PlayerSkin.NORMAL
                    && getPlayerComponent().getState() != DIE) {
                onPlayerDied();
            }
        });
    }

    @Override
    protected void onPreInit() {
        loopBGM("stage_theme.mp3");
    }

    @Override
    protected void onUpdate(double tpf) {
        if (isLoading) {
            return;
        }
        inc("levelTime", -tpf);

        if (getd("levelTime") <= 0.0) {
            showMessage("Time Up !!!");
            onPlayerDied();
            set("levelTime", TIME_LEVEL);
        }
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("level", STARTING_LEVEL);
        vars.put("life", 3);
        vars.put("enemies", 0);
        vars.put("score", 0);
        vars.put("flame", 1);
        vars.put("speed", PLAYER_SPEED);
        vars.put("bomb", 1);
        vars.put("levelTime", TIME_LEVEL);
    }

    @Override
    protected void initUI() {
        Templates.addILabelUI("life", "💜 %d", 160, 25);
        Templates.addILabelUI("enemies", "👻 %d", 1010, 25);
        Templates.addDLabelUI("levelTime", "⏰ %.0f", 1140, 25);
    }

    public void onPlayerDied() {
        isLoading = true;
        getPlayerComponent().setState(DIE);
        getPlayerComponent().setBombInvalidation(true);
        getGameTimer().runOnceAfter(() -> {
            getGameScene().getViewport().fade(() -> {
                inc("life", -1);
                if (geti("life") > 0) {
                    setLevel();
                } else {
                    getSceneService().pushSubScene(new tableplatdessert("   Tu as perdu !!!\n\n\n\n Ta salopette a sauté."));
                }
            });
        }, Duration.seconds(2.2));
    }

    private void setLevel() {
        isLoading = false;
        setLevelFromMap("level" + geti("level") + ".tmx");
        Viewport viewport = getGameScene().getViewport();
        viewport.setBounds(0, 0, GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
        viewport.bindToEntity(getPlayer(), getAppWidth() / 2, getAppHeight() / 2);
        viewport.setLazy(true);

        set("score", temp.get("score"));
        set("levelTime", TIME_LEVEL);
        set("enemies", getGameWorld().getGroup(ENEMY1,
                ENEMY2, ENEMY3, ENEMY4, ENEMY5).getSize());
    }

    private void nextLevel() {
        if (geti("level") == MAX_LEVEL) {
            getSceneService().pushSubScene(new tableplatdessert("Bravo gros!!!\n\n\n\n    Tu as finis tous les plats."));
            return;
        }
        inc("level", +1);
        if (geti("level") > 1) {
            getSceneService().pushSubScene(new tableplatentree());
        }

        temp.put("score", geti("score"));
        temp.put("flame", geti("flame"));
        temp.put("bomb", geti("bomb"));

        setLevel();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
