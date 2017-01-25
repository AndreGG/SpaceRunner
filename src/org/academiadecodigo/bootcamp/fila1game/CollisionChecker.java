
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

        int playerInnerDistanceOnX = playerGetCenterX(player) - player.getSprite().getX();
        int objectInnerDistanceOnX = getObjectCenterX() - obstacle1.getObject().getX();


        if ((distanceOnX < playerInnerDistanceOnX + objectInnerDistanceOnX
                && (player.getSprite().getY() + player.getSprite().getHeight()) > obstacle1.getObject().getY())) {
//            player.setPlayerDead();
            player.setLife();
        }
    }

    public int playerGetCenterX(Player player) {
        return ((player.getSprite().getX() + player.getSprite().getWidth()) / 2);
    }

    public int getObjectCenterX() {
        return ((obstacle1.getObject().getX() + obstacle1.getObject().getWidth()) / 2);
    }


}
