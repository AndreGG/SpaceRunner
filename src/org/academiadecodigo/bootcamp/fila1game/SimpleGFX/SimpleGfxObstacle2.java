package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxObstacle2 extends SimpleGfxGameObjects {

    private Rectangle obstacle1;
    private Picture pic;
    private double startPos;
    private int speed;

    public SimpleGfxObstacle2(int startX, int startY) {
        obstacle1 = new Rectangle(startX,startY, 64, 64);
//        obstacle1.setColor(Color.WHITE);
//        obstacle1.draw();
        pic = new Picture(startX, startY, "obstacleBox.png");
        pic.draw();

        startPos = startX;

        speed = -5;
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
    public int getSpeed() {
        return this.speed;
    }

    public void show() {

    }

    public void hide() {

    }

    @Override
    public void move() {

        if (obstacle1.getX() > 11) {
            obstacle1.translate(speed, 0);
            pic.translate(speed, 0);
        }

        if (obstacle1.getX() <= 11) {
            obstacle1.translate(startPos, 0);
            pic.translate(startPos, 0);
        }

    }
}
