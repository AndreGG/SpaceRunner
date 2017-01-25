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
    private int speed;
    private int width;
    private int height;



    public GameObjects (MovableRepresentable object) {
        this.object = object;
        posX = object.getX();
        posY = object.getY();
        width = object.getWidth();
        height = object.getHeight();

    }

    public MovableRepresentable getObject() {
        return object;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //TODO reached end

}
