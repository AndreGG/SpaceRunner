package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxStage implements MovableRepresentable {

    private Rectangle stage;
    private Rectangle[] borders = new Rectangle[2];
    Picture[] background = new Picture[13];
    Picture[] floor = new Picture[13];
    Picture[] midBackGround = new Picture[13];
    Picture[] highBackGround = new Picture[13];

    private int speed = 1;

    public SimpleGfxStage() {

        stage = new Rectangle(10, 10, 1024, 576);
        stage.setColor(Color.BLACK);
        stage.fill();

       // CRIAR BACKGROUND1
        background[0] = new Picture(stage.getX(), stage.getY(), "backGrounds/farbackground/fbackground_0.png");
        for (int i = 1; i < background.length; i++) {
            background[i] = new Picture(background[i - 1].getX() + 80, stage.getY(), "backGrounds/farbackground/fbackground_" + i + ".png");
        }

        //CRIAR FLOOR
        floor[0] = new Picture(stage.getX(), stage.getY(), "backGrounds/floor/floor_0.png");
        for (int i = 1; i < floor.length; i++) {
            floor[i] = new Picture(floor[i - 1].getX() + 80, stage.getY(), "backGrounds/floor/floor_" + i + ".png");
        }

        //CRIAR MIDBACKGROUND
        midBackGround[0] = new Picture(stage.getX(), stage.getY(), "backGrounds/middleground/middleground_0.png");
        for (int i = 1; i < midBackGround.length; i++) {
            midBackGround[i] = new Picture(midBackGround[i - 1].getX() + 80, stage.getY(), "backGrounds/middleground/middleground_" + i + ".png");
        }

        //CRIAR HIGHBACKGROUND
        highBackGround[0] = new Picture(stage.getX(), stage.getY(), "backGrounds/highground/highground_0.png");
        for (int i = 1; i < highBackGround.length; i++) {
            highBackGround[i] = new Picture(highBackGround[i - 1].getX() + 80, stage.getY(), "backGrounds/highground/highground_" + i + ".png");

        }

    }

    /**
     * Draws all background related assets
     */

    public void show() {

        for (Picture backG : highBackGround) {
            backG.draw();
        }

        for (Picture backG : background) {
            backG.draw();
        }

        for (Picture backG : midBackGround) {
            backG.draw();
        }

        for (Picture backG : floor) {
            backG.draw();
        }

        // BORDAS PRETAS AQUI PARA SEREM CRIADAS DEPOIS
        borders[0] = new Rectangle(stage.getX(), stage.getY(), 80, stage.getHeight());
        borders[0].setColor(Color.BLACK);
        borders[0].fill();

        borders[1] = new Rectangle(stage.getWidth() - 80, stage.getY(), 100, stage.getHeight());
        borders[1].setColor(Color.BLACK);
        borders[1].fill();

    }

    public void hide() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void move() {

        animateBackground();
        animateFloor();
        animateMidBackground();
        animateHighBackground();

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

    private void animateBackground() {

        for (int i = 0; i < background.length; i++) {
            if (background[i].getX() > 11) {
                background[i].translate(-speed*0.7, 0);
            }

            if (background[i].getX() <= 11) {
                background[i].translate(950, 0);
            }
        }
    }

    private void animateFloor() {

        for (int i = 0; i < floor.length; i++) {
            if (floor[i].getX() > 11) {
                floor[i].translate(-speed*5, 0);
            }

            if (floor[i].getX() <= 11) {
                floor[i].translate(950, 0);
            }
        }
    }

    private void animateMidBackground() {

        for (int i = 0; i < midBackGround.length; i++) {
            if (midBackGround[i].getX() > 11) {
                midBackGround[i].translate(-speed, 0);
            }

            if (midBackGround[i].getX() <= 11) {
                midBackGround[i].translate(950, 0);
            }
        }
    }

    private void animateHighBackground() {

        for (int i = 0; i < highBackGround.length; i++) {
            if (highBackGround[i].getX() > 11) {
                highBackGround[i].translate(-speed*0.2, 0);
            }

            if (highBackGround[i].getX() <= 11) {
                highBackGround[i].translate(950, 0);
            }
        }
    }

}

