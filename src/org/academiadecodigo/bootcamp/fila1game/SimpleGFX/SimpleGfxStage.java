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
    private int backCounter;

    public SimpleGfxStage() {

        stage = new Rectangle(10, 10, 1024, 576);
        stage.setColor(Color.WHITE);
        stage.draw();



        Rectangle[] background = new Rectangle[1];
        background[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight());

        for (int i = 1; i < background.length; i++) {
            background[i] = new Rectangle(background[i-1].getX() + 80, stage.getY(), 80, stage.getHeight());
        }


        //POPULAR COM CORES
        for (int i = 0; i < background.length; i++) {
            if (i % 2 == 0){
                background[i].setColor(Color.GREEN);
            } else {
                background[i].setColor(Color.RED);
            }
        }

        for(Rectangle backG: background) {
            backG.fill();
        }

        for (int i = 0; i < background.length; i++) {
            if (background[0].getX() > 11) {
                background[0].translate(-20, 0);
            }

            if (background[0].getX() < 20) {
                background[0].translate(1000, 0);
            }
        }



        // BORDAS PRETAS AQUI PARA SEREM CRIADAS DEPOIS
        borders[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight());
        borders[0].setColor(Color.BLACK);
        borders[0].fill();

        borders[1] = new Rectangle(stage.getWidth() - 54, stage.getY(), 80, stage.getHeight());
        borders[1].setColor(Color.BLACK);
        borders[1].fill();
    }

    @Override
    public int getX() {
        return stage.getX();
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

