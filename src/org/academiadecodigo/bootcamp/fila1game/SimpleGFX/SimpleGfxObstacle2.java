package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxObstacle2 extends SimpleGfxGameObjects {


    private Rectangle sprite;
    private Picture pic;
    private double startPos;
    private int speed;
    private boolean active;

    public SimpleGfxObstacle2(int startX, int startY) {
        sprite = new Rectangle(startX,startY-87, 80, 151);
//        sprite.setColor(Color.WHITE);
//        sprite.fill();
        pic = new Picture(startX, startY-87, "obstacles/ObstacleZ.png");
//        pic.draw();


        startPos = startX;

        speed = -5;
    }

    @Override
    public int getX() {

        return sprite.getX();
    }

    @Override
    public void setY(int i) {

    }

    @Override
    public int getY() {

        return sprite.getY();
    }

    @Override
    public int getWidth() {

        return sprite.getWidth();
    }

    @Override
    public int getHeight() {

        return sprite.getHeight();
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    public void show() {
//        sprite.fill();
        pic.draw();
    }

    public void hide() {
//        sprite.delete();
        pic.delete();
    }

    @Override
    public void move(int speed) {

        this.speed = speed;

        if ((sprite.getX() > 11) && (active == true)) {
            sprite.translate(speed, 0);
            pic.translate(speed, 0);
        }

        if (sprite.getX() <= 11) {
            sprite.translate(startPos, 0);
            pic.translate(startPos, 0);
            active = false;
        }

    }

    @Override
    public void resetPosition(int x, int y) {

    }


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void move() {

    }
}

