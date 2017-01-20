package org.academiadecodigo.bootcamp.fila1game.Representables;

/**
 * Created by codecadet on 1/20/17.
 */
public class Player {

    private MovableRepresentable sprite;

    public MovableRepresentable getSprite() {
        return sprite;
    }

    public Player (MovableRepresentable sprite) {
        this.sprite = sprite;
    }
}
