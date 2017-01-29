package org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects;

import org.academiadecodigo.bootcamp.fila1game.Game;
import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;

/**
 * Created by codecadet on 1/20/17.
 */
public class Obstacle1 extends GameObjects {

    private MovableRepresentable sprite;

    public Obstacle1(MovableRepresentable object) {
        super(object);
        this.sprite = object;
    }

    public void move() {
        sprite.move();
    }

    public void show() {
        sprite.show();
    }


}
