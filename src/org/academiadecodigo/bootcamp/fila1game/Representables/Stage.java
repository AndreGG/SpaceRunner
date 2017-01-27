package org.academiadecodigo.bootcamp.fila1game.Representables;

/**
 * Created by codecadet on 1/20/17.
 */
public class Stage {

    private MovableRepresentable stage;

    public MovableRepresentable getStage() {
        return stage;
    }

    public Stage (MovableRepresentable stage) {
        this.stage = stage;
    }

    public void show() {

    }

    public void move() {
        stage.move();
    }

}
