package org.academiadecodigo.bootcamp.fila1game.Representables;

import org.academiadecodigo.bootcamp.fila1game.CollisionChecker;

/**
 * Created by codecadet on 1/20/17.
 */
public class Player {

    private MovableRepresentable sprite;
    private boolean playerDead;
    private int x;
    private int y;
    private CollisionChecker checker;
    private int width;
    private int height;
    private int startingPosX;
    private int startingPosY;

    public Player (MovableRepresentable sprite) {

        this.sprite = sprite;
        startingPosX = sprite.getX();
        startingPosY = sprite.getY();

    }

    public void move() {
            sprite.move();
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead() {
        playerDead = true;
    }

    public int getX() {
        return sprite.getX();
    }

    public int getY() {
        return sprite.getY();
    }

    public void setChecker(CollisionChecker checker) {
        this.checker = checker;
    }

    public void resetPosition() {
        sprite.resetPosition(startingPosX - sprite.getX(), startingPosY - sprite.getY());
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public MovableRepresentable getSprite() {
        return sprite;
    }
}
