package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;

public class CollisionChecker {

    private GameObjects[] objects;
    private Obstacle1 obstacle1;

    public CollisionChecker(/*GameObjects[] object*/ Obstacle1 obstacle1) {
        /*objects = object;*/
        this.obstacle1 = obstacle1;
    }

    public void checkCollision(Player player) {

        // Distance between the center of each object
        int distanceOnX = getObjectCenterX() - GetPlayerCenterX(player);
        System.out.println("Distance on X :" + distanceOnX);

        int playerInnerDistanceOnX = player.getWidth() / 2;
        int objectInnerDistanceOnX = obstacle1.getWidth() / 2;
        int sumOfInnerDistances = playerInnerDistanceOnX + objectInnerDistanceOnX;

        boolean collision = ((distanceOnX <= sumOfInnerDistances)
                && (player.getY() + player.getHeight()) > obstacle1.getPosY());

//        System.out.println("Sum of inner distances: " + playerInnerDistanceOnX+objectInnerDistanceOnX);
        System.out.println("Sum of inner distances FINAL: " + sumOfInnerDistances);

        // Standing on obstacle
//        if (collision){
//            player.getSprite().setY(100);
//        } else if (!collision && player.getSprite().getY() > 500){
//            player.getSprite().setY(-100);
//            return;
//        }


        // Collision for damage
        if (collision) {
            player.setPlayerDead();
//            player.setLife();
        }
    }

    private int GetPlayerCenterX(Player player) {

        // This is a pixel
        return (player.getX() + (player.getWidth() / 2));

//        return ((player.getSprite().getX() + player.getSprite().getWidth()) / 2);
    }

    private int getObjectCenterX() {

        // This is a pixel
        return (obstacle1.getPosX() + (obstacle1.getWidth() / 2));

//        return ((obstacle1.getObject().getX() + obstacle1.getObject().getWidth()) / 2);
    }

//    private int GetPlayerCenterY(Player player) {
//
//        //This is a pixel
//        return ()
//    }

}
