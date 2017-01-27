package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxObstacle1 extends SimpleGfxGameObjects {

    private Rectangle obstacle1;
    private double startPos;

    public SimpleGfxObstacle1(int startX, int startY) {
        obstacle1 = new Rectangle(startX,startY,80,60);
        obstacle1.setColor(Color.BLUE);
        obstacle1.fill();
        startPos = startX;
    }

    @Override
    public int getX() {
        return obstacle1.getX();
    }

    @Override
    public void setY(int i) {

    }

    @Override
    public int getY() {
        return obstacle1.getY();
    }

    @Override
    public int getWidth() {
        return obstacle1.getWidth();
    }

    @Override
    public int getHeight() {
        return obstacle1.getHeight();
    }

    @Override
    public void move() {

        if (obstacle1.getX() > 11) {
            obstacle1.translate(-2, 0);
        }

        if (obstacle1.getX() <= 11) {
            obstacle1.translate(startPos, 0);
        }

    }
}
