package org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.bootcamp.fila1game.Representables.Representable;

/**
 * Created by codecadet on 1/20/17.
 */
public abstract class GameObjects {

    private MovableRepresentable object;
    private int posX;
    private int posY;

    public MovableRepresentable getObject() {
        return object;
    }

    public GameObjects (MovableRepresentable object) {
        this.object = object;
    }



}
