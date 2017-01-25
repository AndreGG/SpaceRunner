package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.CollisionChecker;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 1/20/17.
 */
public class SimpleGfxPlayer extends SimpleGfxGameObjects implements KeyboardHandler {

    private Rectangle sprite;
    private boolean jumping;
    private Keyboard keyboard = new Keyboard(this);
    private int fallSpeed = 20;
    private int jumpCounter = 2;
    private int count;
    private int jumpArc = 0;



    public SimpleGfxPlayer(int startX, int startY) {
        sprite = new Rectangle(startX + 60, startY,64,64);
        sprite.setColor(Color.RED);
        sprite.fill();
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
        if (sprite.getY() >= 485) {
            count = 0;
            jumpCounter = 2;
            jumpArc = 0;
        }
    }

    @Override
    public int getX() {
        return sprite.getX();
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
    public void move() {


        refreshJumps();

        if (count < 10 && jumping && jumpCounter > 0) {
            sprite.translate(0,-15);
            count++;
            if (jumpCounter > 0) {
                count = 0;
            }

//            System.out.println(sprite.getY());

        } else if (count >= 0 && sprite.getY() < 500) {

            jumpArc++;

            sprite.translate(0, jumpArc);

        }

        /*if (jumping && sprite.getY() > 250 && jumpCounter > 0) {
            sprite.translate(0, -20);
        }
        if (!jumping && sprite.getY() < 500) {
            sprite.translate(0, fallSpeed);
        }
        if (sprite.getY() >= 500) {
            jumpCounter = 2;
        }*/

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
