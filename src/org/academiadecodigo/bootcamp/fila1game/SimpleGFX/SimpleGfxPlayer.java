package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;

import org.academiadecodigo.bootcamp.fila1game.CollisionChecker;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class SimpleGfxPlayer extends SimpleGfxGameObjects implements KeyboardHandler {

    private Rectangle hitbox;
    private Picture[] spriteSheet;
    private boolean jumping;
    private Keyboard keyboard = new Keyboard(this);
    private CollisionChecker checker;
    private int jumpCounter = 2;
    private int count;
    private int speed;
    private int jumpArc = 0;
    private int jumpStart = -15;
    private int animationCount = 0;
    private boolean playerDead;
    private InputStream music = new FileInputStream("Resources/Music/jump.wav");
    private AudioStream jumpSound = new AudioStream(music);

    public SimpleGfxPlayer(int startX, int startY, CollisionChecker checker) throws IOException {

        this.checker = checker;

        hitbox = new Rectangle(startX + 60, startY, 64, 64);

        spriteSheet = new Picture[10];

        for(int i = 0; i < spriteSheet.length; i++) {
            spriteSheet[i] = new Picture(hitbox.getX(), hitbox.getY(), "/spritesheet/sprite_" + i + ".png");
        }

        for(Picture sprite: spriteSheet) {
            sprite.delete();
        }

        keyboardInit();
        speed = 0;

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

        if (isOnFloor() || isOnTopOfObstacle()) {
            count = 0;
            jumpCounter = 2;
            jumpArc = 0;
            jumpStart = -15;
        }

    }

    public void show() {
        spriteSheet[0].draw();
    }

    private void animateSprite() {

        if (!isOnFloor()) {
            for (Picture sprite : spriteSheet) {
                sprite.delete();
            }
            spriteSheet[7].draw();
        }

        if (isOnFloor() || isOnTopOfObstacle()) {
            spriteSheet[7].delete();

            if (animationCount < 6) {
                spriteSheet[1].delete();
                spriteSheet[2].draw();
            } else if (animationCount < 12) {
                spriteSheet[2].delete();
                spriteSheet[3].draw();
            } else if (animationCount < 18) {
                spriteSheet[3].delete();
                spriteSheet[2].draw();
            } else if (animationCount < 24) {
                spriteSheet[2].delete();
                spriteSheet[1].draw();
            } else if (animationCount > 24) {
                animationCount = 0;
            }

        }
        animationCount++;
    }

    @Override
    public void move() {

        if (playerDead) {
            return;
        }

        checker.checkCollision(this);

        animateSprite();
        refreshJumps();

        if (isOnTopOfObstacle()) {

            if (playerDead) {
                playerDead = false;
            }

            jumpArc = checker.distanceFromObjectOnY(this);

            jump();
            refreshJumps();

        } else {

            jump();

        }

    }

    private void jump() {

        if (count < 30 && jumping && jumpCounter > 0) {

            hitbox.translate(0, jumpStart);

            for (Picture sprite : spriteSheet) {
                sprite.translate(0, jumpStart);
            }

            jumpStart++;
            count++;

            if (jumpCounter > 0) {
                count = 0;
                jumpStart = -15;
                jumpArc = 0;
            }

        } else if (count >= 0 && hitbox.getY() < 500) {

            jumpArc++;

            if ((getY()+jumpArc) > 500){
                jumpArc = 500 - getY();
            }

            hitbox.translate(0, jumpArc);

            for (Picture sprite : spriteSheet) {
                sprite.translate(0, jumpArc);
            }

        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            System.out.println("FIRST JUMP COUNTER: " + jumpCounter);

            jumping = true;
            jumpCounter--;

            System.out.println("JUMP COUNTER: " + jumpCounter);

            AudioPlayer.player.start(jumpSound);

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            jumping = false;
        }

    }

    /***
     * Hides all sprites.
     */

    public void hide() {

        for(Picture sprites: spriteSheet) {
            sprites.delete();
        }

    }

    public void setPlayerDead() {
        playerDead = true;
    }

    /***
     * Checks if player is above an obstacle.
     * @return
     */

    private boolean isOnTopOfObstacle() {
        return (checker.distanceFromObjectOnY(this) < jumpArc) && (checker.isOnXWithObject(this) == true);
    }

    private boolean isOnFloor() {

        return hitbox.getY() >= 500;

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
    public void setY(int i) {
        hitbox.translate(0, i);
    }

    public int getSpeed() {
        return this.speed;
    }

}