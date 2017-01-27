package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.Representables.Representable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxStage implements Representable {

    private Rectangle stage;
    private Rectangle[] borders = new Rectangle[2];

    public SimpleGfxStage() {

        stage = new Rectangle(10, 10, 1024, 576);
        stage.setColor(Color.WHITE);
        stage.draw();

        borders[0] = new Rectangle(stage.getX(),stage.getY(),80,stage.getHeight());
        borders[0].setColor(Color.BLACK);
        borders[0].fill();

        borders[1] = new Rectangle(stage.getWidth()-70,stage.getY(),80,stage.getHeight());
        borders[1].setColor(Color.BLACK);
        borders[1].fill();

    }

    @Override
    public int getX() {
        return stage.getX();
    }

    @Override
    public void setY(int i) {

    }

    @Override
    public int getY() {
        return stage.getY();
    }

    @Override
    public int getWidth() {
        return stage.getWidth();
    }

    @Override
    public int getHeight() {
        return stage.getHeight();
    }
}
