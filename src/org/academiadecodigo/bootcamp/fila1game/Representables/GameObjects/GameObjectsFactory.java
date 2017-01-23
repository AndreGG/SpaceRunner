package org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects;

import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;

/**
 * Created by codecadet on 1/20/17.
 */
public interface GameObjectsFactory {

    MovableRepresentable getNewGO(GameObjectsType type);

}
