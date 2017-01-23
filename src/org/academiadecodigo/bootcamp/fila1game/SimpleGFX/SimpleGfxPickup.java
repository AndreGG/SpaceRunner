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
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void move() {

    }
}
