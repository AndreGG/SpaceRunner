package org.academiadecodigo.bootcamp.fila1game.Representables;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 1/20/17.
 */
public interface MovableRepresentable extends Representable {

    void move();

    void setActive(boolean active);

    boolean isActive();

    Rectangle getRectangle();

}
