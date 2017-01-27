package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
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
    private Picture[] spriteSheet;
    private boolean jumping;
    private Keyboard keyboard = new Keyboard(this);
    private int jumpCounter = 3;
    private int count;
    private int jumpArc = 0;
    private int jumpStart = -15;
    private int animationCount = 0;



    public SimpleGfxPlayer(int startX, int startY) {

        hitbox = new Rectangle(startX + 60, startY,64,64);
        keyboardInit();

        spriteSheet = new Picture[4];

        spriteSheet[0] = new Picture(hitbox.getX(), hitbox.getY(), "walk01.png");
        spriteSheet[1] = new Picture(hitbox.getX(), hitbox.getY(), "walk02.png");
        spriteSheet[2] = new Picture(hitbox.getX(), hitbox.getY(), "walk03.png");
        spriteSheet[3] = new Picture(hitbox.getX(), hitbox.getY(), "jump.png");

        for(Picture sprite: spriteSheet) {
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

    private void refreshJumps() {

        if (hitbox.getY() >= 485) {
            count = 0;
            jumpCounter = 3;
            jumpArc = 0;
            jumpStart = -15;
        }
    }

    private void animateSprite() {

        if (hitbox.getY() < 485) {
            for (Picture sprite: spriteSheet) {
                sprite.delete();
            }
            spriteSheet[3].draw();
        }

        if (hitbox.getY() >= 485){
            spriteSheet[3].delete();

            if (animationCount < 6) {
                spriteSheet[0].delete();
                spriteSheet[1].draw();
            } else if (animationCount < 12) {
                spriteSheet[1].delete();
                spriteSheet[2].draw();
            } else if (animationCount < 18) {
                spriteSheet[2].delete();
                spriteSheet[1].draw();
            } else if (animationCount < 24) {
                spriteSheet[1].delete();
                spriteSheet[0].draw();
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

        //System.out.println(count + " count");
        //System.out.println(jumpCounter + " JC");

        if (count < 10 && jumping && jumpCounter > 0) {

            hitbox.translate(0,jumpStart);

            for (Picture word: spriteSheet) {
                word.translate(0, jumpStart);
            }

            jumpStart++;
            count++;

            if (jumpCounter > 0) {
                count = 0;
                jumpStart = -15;
            }

        } else if (count >= 0 && hitbox.getY() < 500) {

            jumpArc++;

            hitbox.translate(0, jumpArc);

            for (Picture word: spriteSheet) {
                word.translate(0, jumpArc);
            }



        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            jumping = true;
            jumpCounter--;
            System.out.println(jumpCounter);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            jumping = false;
        }
    }


}
