package org.academiadecodigo.bootcamp.fila1game.Representables;

/**
 * Created by codecadet on 1/20/17.
 */
public interface MovableRepresentable extends Representable {

    void move();

    void setActive(boolean active);

    boolean isActive();


}
