package org.academiadecodigo.bootcamp.fila1game;

import com.sun.javafx.PlatformUtil;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;

public class CollisionChecker {

    private GameObjects[] objects;
    private Obstacle1 obstacle1;

    public CollisionChecker(/*GameObjects[] object*/ Obstacle1 obstacle1) {
        /*objects = object;*/

        this.obstacle1 = obstacle1;
    }



    public void checkCollision(Player player) {

        int distanceOnX = getObjectCenterX() - playerGetCenterX(player);
        int distanceOnY = getObjectCenterY() - playerGetCenterY(player);

        int playerInnerDistanceOnX = playerGetCenterX(player) - player.getSprite().getX();
        int playerInnerDistanceOnY = playerGetCenterY(player) - player.getSprite().getY();
        int objectInnerDistanceOnX = getObjectCenterX() - obstacle1.getObject().getX();
        int objectInnerDistanceOnY = getObjectCenterY() - obstacle1.getObject().getY();

//        System.out.println(getObjectCenterX());
//        System.out.println(playerGetCenterY(player));

        if ((distanceOnX < playerInnerDistanceOnX + objectInnerDistanceOnX
                && (player.getSprite().getY() + player.getSprite().getHeight()) > obstacle1.getObject().getY()))
                /*|| distanceOnY < playerInnerDistanceOnY + objectInnerDistanceOnY)*/ {
            System.out.println("game over");
            player.setPlayerDead();
        }

        System.out.println("running");
    }

    public int playerGetCenterX(Player player){
        return ((player.getSprite().getX() + player.getSprite().getWidth())/2);
    }

    public int playerGetCenterY(Player player){
        return ((player.getSprite().getY() + player.getSprite().getHeight())/2);
    }

    public int getObjectCenterX(){
        return ((obstacle1.getObject().getX() + obstacle1.getObject().getWidth())/2);
    }

    public int getObjectCenterY(){
        return ((obstacle1.getObject().getY() + obstacle1.getObject().getHeight())/2);
    }

}
