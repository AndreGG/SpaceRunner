package org.academiadecodigo.bootcamp.fila1game.Representables;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 1/20/17.
 */
public class Player {

    private MovableRepresentable sprite;
    private boolean jumping;

    public MovableRepresentable getSprite() {
        return sprite;
    }

    public Player (MovableRepresentable sprite) {
        this.sprite = sprite;
    }

    public void move() {
        sprite.move();
    }
}
