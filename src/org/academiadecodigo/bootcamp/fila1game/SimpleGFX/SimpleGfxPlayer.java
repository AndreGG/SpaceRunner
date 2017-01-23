package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxPlayer extends SimpleGfxGameObjects {

    private Rectangle sprite;

    public SimpleGfxPlayer(int startX, int startY) {
        sprite = new Rectangle(startX,startY,64,64);
        sprite.setColor(Color.RED);
        sprite.fill();
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
        // translate
    }

}
