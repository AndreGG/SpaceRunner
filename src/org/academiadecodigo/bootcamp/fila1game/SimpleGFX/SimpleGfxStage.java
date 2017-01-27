package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxStage implements MovableRepresentable {

    private Rectangle stage;
    private Rectangle[] borders = new Rectangle[2];
    Rectangle[] background = new Rectangle[13];
    Rectangle[] floor = new Rectangle[13];
    Rectangle[] midBackGround = new Rectangle[13];
    Rectangle[] highBackGround = new Rectangle[13];
    private int speed = 1;


    public SimpleGfxStage() {

        stage = new Rectangle(10, 10, 1024, 576);
        stage.setColor(Color.WHITE);
        stage.draw();

        // CRIAR BACKGROUND1
        background[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight());
        for (int i = 1; i < background.length; i++) {
            background[i] = new Rectangle(background[i - 1].getX() + 80, stage.getY(), 80, stage.getHeight());
        }

        //CRIAR FLOOR
        floor[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight()-100);
        for (int i = 1; i < floor.length; i++) {
            floor[i] = new Rectangle(floor[i - 1].getX() + 80, stage.getY(), 80, stage.getHeight()-100);
        }

        //CRIAR MIDBACKGROUND
        midBackGround[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight()-200);
        for (int i = 1; i < midBackGround.length; i++) {
            midBackGround[i] = new Rectangle(floor[i - 1].getX() + 80, stage.getY(), 80, stage.getHeight()-200);
        }

        //CRIAR HIGHBACKGROUND
        highBackGround[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight()-400);
        for (int i = 1; i < highBackGround.length; i++) {
            highBackGround[i] = new Rectangle(floor[i - 1].getX() + 80, stage.getY(), 80, stage.getHeight()-400);
        }

        //RECTANGULOS BACKGROUND1 COM CORES ALTERNADAS TEMPORARIO
        for (int i = 0; i < background.length; i++) {
            if (i % 2 == 0) {
                background[i].setColor(Color.GREEN);
            } else {
                background[i].setColor(Color.RED);
            }
        }

        //RECTANGULOS FLOOR COM CORES ALTERNADAS TEMPORARIO
        for (int i = 0; i < floor.length; i++) {
            if (i % 2 == 0) {
                floor[i].setColor(Color.CYAN);
            } else {
                floor[i].setColor(Color.MAGENTA);
            }
        }

        //RECTANGULOS MIDBACKGROUND COM CORES ALTERNADAS TEMPORARIO
        for (int i = 0; i < midBackGround.length; i++) {
            if (i % 2 == 0) {
                midBackGround[i].setColor(Color.ORANGE);
            } else {
                midBackGround[i].setColor(Color.YELLOW);
            }
        }

        //RECTANGULOS HIGHBACKGROUND COM CORES ALTERNADAS TEMPORARIO
        for (int i = 0; i < highBackGround.length; i++) {
            if (i % 2 == 0) {
                highBackGround[i].setColor(Color.DARK_GRAY);
            } else {
                highBackGround[i].setColor(Color.PINK);
            }
        }

        for (Rectangle backG : background) {
            backG.fill();
        }
        for (Rectangle backG : floor) {
            backG.fill();
        }
        for (Rectangle backG : midBackGround) {
            backG.fill();
        }
        for (Rectangle backG : highBackGround) {
            backG.fill();
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
    public void move() {
        animateBackground1();
        animateFloor1();
        animateMidBackground();
        animateHighBackground();
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

    public void animateBackground1() {

        for (int i = 0; i < background.length; i++) {
            if (background[i].getX() > 11) {
                background[i].translate(-speed*5, 0);
            }

            if (background[i].getX() <= 11) {
                background[i].translate(950, 0);
            }
        }
    }

    public void animateFloor1() {

        for (int i = 0; i < floor.length; i++) {
            if (floor[i].getX() > 11) {
                floor[i].translate(-speed*3, 0);
            }

            if (floor[i].getX() <= 11) {
                floor[i].translate(950, 0);
            }
        }
    }

    private void animateMidBackground() {
        for (int i = 0; i < midBackGround.length; i++) {
            if (midBackGround[i].getX() > 11) {
                midBackGround[i].translate(-speed*2, 0);
            }

            if (midBackGround[i].getX() <= 11) {
                midBackGround[i].translate(950, 0);
            }
        }
    }

    private void animateHighBackground() {
        for (int i = 0; i < highBackGround.length; i++) {
            if (highBackGround[i].getX() > 11) {
                highBackGround[i].translate(-speed, 0);
            }

            if (highBackGround[i].getX() <= 11) {
                highBackGround[i].translate(950, 0);
            }
        }
    }

}

