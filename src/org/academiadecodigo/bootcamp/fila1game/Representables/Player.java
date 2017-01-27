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
    private int life = 10;

    public MovableRepresentable getSprite() {
        return sprite;
    }

    public Player (MovableRepresentable sprite) {
        this.sprite = sprite;
    }

    public void move() {

        checker.checkCollision(this);
        System.out.println("Dead: " + playerDead);
        System.out.println();
//        System.out.println("Life: " + life);
//        System.out.println();

//        if (life > 0) {
//            sprite.move();
//        }

        if (!playerDead) {
            sprite.move();
        }
    }

    private boolean isPlayerDead() {
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

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public void setLife() {
        life--;
    }
}
