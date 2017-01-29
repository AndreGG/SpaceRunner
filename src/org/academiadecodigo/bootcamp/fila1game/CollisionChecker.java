package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;

public class CollisionChecker {

    private GameObjects activeObject;

    public CollisionChecker() {
    }

    public void setActiveObject(GameObjects activeObject) {
        this.activeObject = activeObject;
    }

    public void checkCollision(SimpleGfxPlayer player) {

        // Distance between the center of each object
        int distanceOnX = getObjectCenterX() - getPlayerCenterX(player);
        int distanceOnY = getObjectCenterY() - getPlayerCenterY(player);

//        System.out.println("Distance on X :" + distanceOnX);
       // System.out.println("Distance on Y :" + distanceOnY);

        int playerInnerDistanceOnX = player.getWidth() / 2;
        int objectInnerDistanceOnX = activeObject.getWidth() / 2;
        int sumOfInnerDistancesOnX = playerInnerDistanceOnX + objectInnerDistanceOnX;

        int playerInnerDistanceOnY = player.getHeight() / 2;
        int objectInnerDistanceOnY = activeObject.getHeight() / 2;
        int sumOfInnerDistancesOnY = playerInnerDistanceOnY + objectInnerDistanceOnY;

        boolean collision = ((((player.getX()+player.getWidth()) > activeObject.getPosX())
                && (player.getX() < (activeObject.getPosX()+ activeObject.getWidth()))
                && (distanceOnY < sumOfInnerDistancesOnY)));

        int playerRightX = player.getX() + player.getWidth();
        int playerDownLeftPix = player.getY() + player.getHeight();

        int obstacleLeftX = activeObject.getPosX();
        int obstacleUpLeftPix = activeObject.getPosY();

        boolean collision2 = ((playerRightX - obstacleLeftX) >= activeObject.getSpeed()) && playerDownLeftPix > obstacleUpLeftPix
                && player.getX() < activeObject.getPosX();



        // Collision for damage

        if (collision2) {
            player.setPlayerDead();
//            player.setLife();
            System.out.println("COLLISION");
        }
    }

    private int getPlayerCenterX(SimpleGfxPlayer player) {
        // This is a pixel
        return (player.getX() + (player.getWidth() / 2));
    }

    private int getObjectCenterX() {
        // This is a pixel
        return (activeObject.getPosX() + (activeObject.getWidth() / 2));
    }

    private int getPlayerCenterY(SimpleGfxPlayer player) {
        //This is a pixel
        return (player.getY() + (player.getHeight() / 2));
    }

    private int getObjectCenterY() {
        //this is a pixel
        return (activeObject.getPosY() + (activeObject.getHeight() / 2));
    }

    public int distanceFromObjectOnY(SimpleGfxPlayer player) {
        return activeObject.getPosY() - (player.getY() + player.getHeight());
    }

    public boolean isOnXWithObject(SimpleGfxPlayer player){
        return ((player.getX()+player.getWidth()) > activeObject.getPosX())
                && (player.getX() < (activeObject.getPosX()+ activeObject.getWidth()));
    }

    public boolean xIsPassed(SimpleGfxPlayer player){
        System.out.println(player.getX());
        System.out.println(activeObject.getPosX()+ activeObject.getWidth());
        return player.getX() >= (activeObject.getPosX()+ activeObject.getWidth()) ;
    }

}
