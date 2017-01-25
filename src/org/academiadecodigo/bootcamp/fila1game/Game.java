package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.Representables.Stage;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxObstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxStage;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

/**
 * Created by codecadet on 1/20/17.
 */
public class Game {

    private GameObjects[] gameObjects;
    private Keyboard keyboard;

    private Stage stage;
    private Player player;
    private Obstacle1 obstacle1;

    public Game() {

        player = new Player(new SimpleGfxPlayer(70,500));
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934,500));
        stage = new Stage(new SimpleGfxStage());

        CollisionChecker checker = new CollisionChecker(obstacle1);

        player.setChecker(checker);

    }

    public void start() {

        obstacle1.move();
        player.move();

    }



}
