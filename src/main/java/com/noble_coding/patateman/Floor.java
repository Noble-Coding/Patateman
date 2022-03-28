package com.noble_coding.patateman;

import java.util.*;

public class Floor {

    // Constants are static by definition.
    private final static double CHANCE_FOR_BREAKABLE_BLOCK = 0.4;
    private final FloorTile[][] tiles;
    private int width;
    private int height;
    private Collection<FloorListener> floorListeners = new ArrayList<>();
    private boolean isGameOver = false;

    public Floor(int width, int height, int nrOfEnemies) {
        this.width = width;
        this.height = height;
        this.tiles = new FloorTile[height][width];
        placeBreakable();
        placeUnbreakableAndGrass();
    }

    public FloorTile getFloorTile(int rowIndex, int colIndex) {
        return tiles[rowIndex][colIndex];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean value) {
        isGameOver = value;
    }

    public void addFloorListener(FloorListener bl) {
        floorListeners.add(bl);
    }

    public void notifyListeners() {
        for (FloorListener b : floorListeners) {
            b.floorChanged();
        }
    }

    private void placeBreakable () {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double r = Math.random();
                if (r < CHANCE_FOR_BREAKABLE_BLOCK) {
                    tiles[i][j] = FloorTile.BREAKABLEBLOCK;
                }
            }
        }
        clearSpawn();
    }

    private void clearSpawn () {
        tiles[1][1] = FloorTile.FLOOR;
        tiles[1][2] = FloorTile.FLOOR;
        tiles[2][1] = FloorTile.FLOOR;
    }

    private void placeUnbreakableAndGrass () {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //Makes frame of unbreakable
                if ((i == 0) || (j == 0) || (i == height - 1) || (j == width - 1) || i % 2 == 0 && j % 2 == 0) {
                    tiles[i][j] = FloorTile.UNBREAKABLEBLOCK;
                    //Every-other unbreakable
                } else if (tiles[i][j] != FloorTile.BREAKABLEBLOCK) {
                    tiles[i][j] = FloorTile.FLOOR;
                }
            }
        }
    }

}
