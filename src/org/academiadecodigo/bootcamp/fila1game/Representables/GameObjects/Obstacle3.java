package org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;

/**
 * Created by codecadet on 1/20/17.
 */
public class Obstacle3 extends GameObjects {

    private MovableRepresentable sprite;

    public Obstacle3(MovableRepresentable object) {
        super(object);
        this.sprite = object;
    }

    public void move() {
        sprite.move();
    }



}
