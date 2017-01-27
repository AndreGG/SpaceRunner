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
    private int life = 50;

    public MovableRepresentable getSprite() {
        return sprite;
    }

    public Player (MovableRepresentable sprite) {

        this.sprite = sprite;
        x = sprite.getX();
        y = sprite.getY();
        width = sprite.getWidth();
        height = sprite.getHeight();

    }

    public void move() {

        checker.checkCollision(this);
        System.out.println(life);

        if (life > 0) {
            sprite.move();
        }

//        if (!playerDead) {
//            sprite.move();
//        }
    }

    private boolean isPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead() {
        playerDead = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setChecker(CollisionChecker checker) {
        this.checker = checker;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setLife() {
        life--;
    }
}
