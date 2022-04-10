package Bomberman;

import Bomberman.Components.Enemy.*;
import Bomberman.Components.aperoRecette;
import Bomberman.Components.courtChemin;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import Bomberman.Components.aperoEclate;
import Bomberman.Components.Fermier;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import static Bomberman.Constants.ConstantCode.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class GameFactory implements EntityFactory {
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .view(new Rectangle(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, Color.rgb(0, 125, 0)))
                .zIndex(-100)
                .with(new IrremovableComponent())
                .build();
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.BRICK)
                .view("brick.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick_break")
    public Entity newBrickBreak(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(GameType.BRICK_BREAK)
                .with(new courtChemin())
                .viewWithBBox(new Rectangle(TILED_SIZE, TILED_SIZE, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .zIndex(1)
                .build();
    }



    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        return entityBuilder(data)
                .type(GameType.PLAYER)
                .bbox(new HitBox(new Point2D(2, 2), BoundingShape.circle(20)))
                .with(physics)
                .with(new Fermier())
                .with(new CollidableComponent(true))
                .zIndex(5)
                .build();
    }

    @Spawns("enemy1")
    public Entity newEnemy1(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.ENEMY1)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(38, 38)))
                .with(new Chips1())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("enemy2")
    public Entity newEnemy2(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.ENEMY2)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(38, 38)))
                .with(new Chips2())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("enemy3")
    public Entity newEnemy3(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.ENEMY3)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(38, 38)))
                .with(new Chips3())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("enemy4")
    public Entity newEnemy4(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.ENEMY4)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(38, 38)))
                .with(new Chips4())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("enemy5")
    public Entity newEnemy5(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.ENEMY5)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(38, 38)))
                .with(new Chips5())
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.DOOR)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("bomb")
    public Entity newBomb(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.BOMB)
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .bbox(new HitBox(new Point2D(2, 2), BoundingShape.circle(22)))
                .with(new aperoEclate())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("virtual_bomb")
    public Entity newVirtualBomb(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(GameType.VIRTUAL_BOMB)
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(TILED_SIZE, TILED_SIZE)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("central_flame")
    public Entity newCFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(2, 2), BoundingShape.box(TILED_SIZE - 4, TILED_SIZE - 4)))
                .with(new aperoRecette("central_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("top_down_flame")
    public Entity newTDFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(2, TILED_SIZE - data.getZ() - 3), BoundingShape.box(TILED_SIZE - 4, data.getZ())))
                .with(new aperoRecette("top_down_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("top_up_flame")
    public Entity newTUFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(2, 3), BoundingShape.box(TILED_SIZE - 4, data.getZ())))
                .with(new aperoRecette("top_up_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("top_right_flame")
    public Entity newTRFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(TILED_SIZE - data.getZ() - 3, 2), BoundingShape.box(data.getZ(), TILED_SIZE - 4)))
                .with(new aperoRecette("top_right_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("top_left_flame")
    public Entity newTLFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(3, 2), BoundingShape.box(data.getZ(), TILED_SIZE - 4)))
                .with(new aperoRecette("top_left_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("up_flame")
    public Entity newUFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(2, 3), BoundingShape.box(TILED_SIZE - 4, data.getZ())))
                .with(new aperoRecette("up_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("down_flame")
    public Entity newDFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(2, TILED_SIZE - data.getZ() - 3), BoundingShape.box(TILED_SIZE - 4, data.getZ())))
                .with(new aperoRecette("down_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("left_flame")
    public Entity newLFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(3, 2), BoundingShape.box(data.getZ(), TILED_SIZE - 4)))
                .with(new aperoRecette("left_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("right_flame")
    public Entity newRFlame(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.FLAME)
                .bbox(new HitBox(new Point2D(TILED_SIZE - data.getZ() - 3, 2), BoundingShape.box(data.getZ(), TILED_SIZE - 4)))
                .with(new aperoRecette("right_flame.png"))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }


    @Spawns("powerup_flames")
    public Entity newItem(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.POWERUP_FLAMES)
                .view("powerup_flames.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("powerup_bombs")
    public Entity newItem2(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.POWERUP_BOMBS)
                .view("powerup_bombs.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("powerup_speed")
    public Entity newItem3(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.POWERUP_SPEED)
                .view("powerup_speed.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("powerup_flamepass")
    public Entity newItem4(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(GameType.POWERUP_FLAMEPASS)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .view("powerup_flamepass.png")
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

    @Spawns("powerup_life")
    public Entity newItem5(SpawnData data) {
        return entityBuilder(data)
                .type(GameType.POWERUP_LIFE)
                .view("powerup_life.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .zIndex(-1)
                .build();
    }

}
