package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxPickup extends SimpleGfxGameObjects {

    private Rectangle pickup;

    public SimpleGfxPickup(int startX, int startY) {
        this.pickup = new Rectangle(startX,startY,100,60);
    }

    @Override
    public int getX() {
        return pickup.getX();
    }

    @Override
    public void setY(int i) {

    }

    @Override
    public int getY() {
        return pickup.getY();
    }

    @Override
    public int getWidth() {
        return pickup.getWidth();
    }

    @Override
    public int getHeight() {
        return pickup.getHeight();
    }

    @Override
    public void show() {
       return;
    }

    @Override
    public void hide() {
        return;
    }

    @Override
    public void move() {
        return;
    }
}
