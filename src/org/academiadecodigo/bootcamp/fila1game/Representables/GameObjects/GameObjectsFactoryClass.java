package org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;

/**
 * Created by codecadet on 1/23/17.
 */
public class GameObjectsFactoryClass {

    private GameObjectsFactory factory;

    public GameObjectsFactoryClass(GameObjectsFactory factory) {
        this.factory = factory;
    }

    public GameObjects createObject(GameObjectsType type) {

        GameObjects gameObject = null;

        switch (type) {

            case OBSTACLE1:
                gameObject = new Obstacle1((MovableRepresentable) factory.getNewGO(type));
                break;
            case PICKUP:
                gameObject = new Pickup((MovableRepresentable) factory.getNewGO(type));

        }

        return gameObject;

    }


}
