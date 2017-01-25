package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.CollisionChecker;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxPlayer extends SimpleGfxGameObjects implements KeyboardHandler {

    private Rectangle hitbox;
    private Picture[] sprites;
    private Picture jump;
    private boolean jumping;
    private Keyboard keyboard = new Keyboard(this);
    private int fallSpeed = 20;
    private int jumpCounter = 2;
    private int count;
    private int jumpArc = 0;
    private int jumpStart = -15;
    private int animationCount = 0;



    public SimpleGfxPlayer(int startX, int startY) {
        sprites = new Picture[4];
        hitbox = new Rectangle(startX + 60, startY,64,64);
        sprites[0] = new Picture(hitbox.getX(), hitbox.getY(), "walk01.png");
        sprites[1] = new Picture(hitbox.getX(), hitbox.getY(), "walk02.png");
        sprites[2] = new Picture(hitbox.getX(), hitbox.getY(), "walk03.png");
        sprites[3] = new Picture(hitbox.getX(), hitbox.getY(), "jump.png");

        for(Picture sprite: sprites) {
            sprite.delete();
        }

        keyboardInit();
    }

    public void keyboardInit() {

        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_SPACE);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(jump);

        KeyboardEvent jumpDown = new KeyboardEvent();
        jumpDown.setKey(KeyboardEvent.KEY_SPACE);
        jumpDown.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(jumpDown);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent jumpSpeed = new KeyboardEvent();
        jumpSpeed.setKey(KeyboardEvent.KEY_DOWN);
        jumpSpeed.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(jumpSpeed);
    }

    public boolean jumpAvailable() {
        return true;
    }

    private void refreshJumps() {
        if (hitbox.getY() >= 485) {
            count = 0;
            jumpCounter = 2;
            jumpArc = 0;
            jumpStart = -15;
        }
    }

    private void animateSprite() {

        System.out.println("animation count" + animationCount);
        System.out.println("sprite getY" + hitbox.getY());

        if (hitbox.getY() < 485) {
            for (Picture sprite: sprites) {
                sprite.delete();
            }
            sprites[3].draw();
        }

        if (hitbox.getY() >= 485){
            sprites[3].delete();
            if (animationCount < 6) {
                sprites[0].delete();
                sprites[1].draw();
            } else if (animationCount < 12) {
                sprites[1].delete();
                sprites[2].draw();
            } else if (animationCount < 18) {
                sprites[2].delete();
                sprites[1].draw();
            } else if (animationCount < 24) {
                sprites[1].delete();
                sprites[0].draw();
            } else if (animationCount > 24) {
                animationCount = 0;
            }
        }
        animationCount++;
    }

    @Override
    public int getX() {
        return hitbox.getX();
    }

    @Override
    public int getY() {
        return hitbox.getY();
    }

    @Override
    public int getWidth() {
        return hitbox.getWidth();
    }

    @Override
    public int getHeight() {
        return hitbox.getHeight();
    }

    @Override
    public void move() {

        animateSprite();
        refreshJumps();

        if (count < 4 && jumping && jumpCounter > 0) {

            hitbox.translate(0,jumpStart);
            for (Picture word: sprites) {
                word.translate(0, jumpStart);
            }

            count++;
            jumpStart++;

            if (jumpCounter > 0) {
                count = 0;
                jumpStart = -15;
            }

        } else if (count >= 0 && hitbox.getY() < 500) {

            jumpArc++;

            hitbox.translate(0, jumpArc);
            for (Picture word: sprites) {
                word.translate(0, jumpArc);
            }

        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            jumping = true;
            jumpCounter--;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            jumping = false;
        }
    }


}
