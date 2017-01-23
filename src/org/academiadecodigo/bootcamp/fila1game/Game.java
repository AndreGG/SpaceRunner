package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.Representables.Stage;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxObstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxStage;

/**
 * Created by codecadet on 1/20/17.
 */
public class Game {

    private GameObjects[] gameObjects;

    private Stage stage;
    private Player player;
    private Obstacle1 obstacle1;

    public Game() {
        stage = new Stage(new SimpleGfxStage());
        player = new Player(new SimpleGfxPlayer(70,500));
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(920,500));
    }



}
