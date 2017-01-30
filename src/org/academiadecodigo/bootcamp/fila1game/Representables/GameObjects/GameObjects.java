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
    private boolean active;



    public GameObjects (MovableRepresentable object) {
        this.object = object;
    }

    public MovableRepresentable getObject() {
        return object;
    }

    public int getPosX() {
        return object.getX();
    }

    public int getPosY() {
        return object.getY();
    }

    public int getHeight() {
        return object.getHeight();
    }

    public int getWidth() {
        return object.getWidth();
    }

    public void move(){
        object.move();
    }

    public int getSpeed() { return object.getSpeed(); }

    public void show() {
        object.show();
    }

    public void delete(){

    }
    //TODO reached end


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
