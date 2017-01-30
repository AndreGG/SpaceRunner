package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;

public class CollisionChecker {

    private GameObjects[] objects;
    private Obstacle1 obstacle1;

    public CollisionChecker(GameObjects[] object) {
        objects = object;
    }

    public void checkCollision(SimpleGfxPlayer player) {

        // Distance between the center of each object
        int distanceOnX = getObjectCenterX() - getPlayerCenterX(player);
        int distanceOnY = getObjectCenterY() - getPlayerCenterY(player);

        int playerInnerDistanceOnX = player.getWidth() / 2;
        int objectInnerDistanceOnX = objects[i].getWidth() / 2;
        int sumOfInnerDistancesOnX = playerInnerDistanceOnX + objectInnerDistanceOnX;

        int playerInnerDistanceOnY = player.getHeight() / 2;
        int objectInnerDistanceOnY = objects[i].getHeight() / 2;
        int sumOfInnerDistancesOnY = playerInnerDistanceOnY + objectInnerDistanceOnY;


        int playerRightX = player.getX() + player.getWidth();
        int playerDownLeftPix = player.getY() + player.getHeight();

        int obstacleLeftX = obstacle1.getPosX();
        int obstacleUpLeftPix = obstacle1.getPosY();

        boolean collision2 = ((playerRightX - obstacleLeftX) >= obstacle1.getSpeed()) && playerDownLeftPix > obstacleUpLeftPix
                && player.getX() < obstacle1.getPosX();

        player.getY() + player.getHeight()) >obstacle1.getPosY()



        int playerRightX = player.getX() + player.getWidth();
        int playerDownLeftPix = player.getY() + player.getHeight();

        int obstacleLeftX = objects[i].getPosX();
        int obstacleUpLeftPix = objects[i].getPosY();

        boolean collision2 = ((playerRightX - obstacleLeftX) >= objects[i].getSpeed())
                && playerDownLeftPix > obstacleUpLeftPix
                && player.getX() < objects[i].getPosX();


        // For damage
//        if (collision) {
//            player.setPlayerDead();
//        }

        // Standing on top of object
        if (collision2) {
            player.setPlayerDead();
        }

    }

    private int getPlayerCenterX(SimpleGfxPlayer player) {
        // This is a pixel
        return (player.getX() + (player.getWidth() / 2));
    }

    private int getObjectCenterX() {
        // This is a pixel
        return (obstacle1.getPosX() + (obstacle1.getWidth() / 2));
    }

    private int getPlayerCenterY(SimpleGfxPlayer player) {
        //This is a pixel
        return (player.getY() + (player.getHeight() / 2));
    }

    private int getObjectCenterY() {
        //this is a pixel
        return (obstacle1.getPosY() + (obstacle1.getHeight() / 2));
    }

    public int distanceFromObjectOnY(SimpleGfxPlayer player) {

        int j = 0;

        for (int i = 0; i < objects.length; i++) {
            j = objects[i].getPosY() - (player.getY() + player.getHeight());
        }

        return j;

    }

    public boolean isOnXWithObject(SimpleGfxPlayer player) {

        boolean b = false;

        for (int i = 0; i < objects.length; i++) {
            b = ((player.getX() + player.getWidth()) > objects[i].getPosX())
                    && (player.getX() < (objects[i].getPosX() + objects[i].getWidth()));
        }

        return b;

    }

    public GameObjects getObject(GameObjects object) {
        return object;
    }


}
