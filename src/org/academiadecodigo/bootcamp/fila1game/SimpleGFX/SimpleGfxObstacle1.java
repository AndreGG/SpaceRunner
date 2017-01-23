package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxObstacle1 extends SimpleGfxGameObjects {

    private Rectangle obstacle1;

    public SimpleGfxObstacle1(int startX, int startY) {
        obstacle1 = new Rectangle(startX,startY,100,60);
        obstacle1.setColor(Color.BLUE);
        obstacle1.fill();
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
