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
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by codecadet on 1/20/17.
 */
public class Game {

    private GameObjects[] gameObjects;
    private Keyboard keyboard;
    private Stage stage;
    private Player player;
    private Obstacle1 obstacle1;
    private InputStream music = new FileInputStream("Resources/Music/SpaceRun.wav");
    private AudioStream gameMusic = new AudioStream(music);
    private boolean musicPlaying;
    private int musicLength;

    // TODO private ActiveBlock;

    public Game() throws IOException {

        stage = new Stage(new SimpleGfxStage());
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934, 500));
        CollisionChecker checker = new CollisionChecker(obstacle1);
        player = new Player(new SimpleGfxPlayer(70, 500, checker));
        musicLength = music.available();

    }

    public void start() throws IOException {

        checkMusicPlaying();
        System.out.println(musicLength);
        if (!musicPlaying) {
            music();
        }

        obstacle1.move();
        stage.move();
        player.move();

    }

    private void music() throws IOException {

        try {
            playMusic();
        } catch (IOException message) {
            System.err.println("Error: " + message.getMessage());
        }
    }

    private void playMusic() throws IOException {

        musicPlaying = true;
        AudioPlayer.player.start(gameMusic);
        AudioPlayer.activeCount();
    }

    private void checkMusicPlaying() throws IOException {
        if (musicLength != 0 && musicPlaying) {
            musicLength = music.available();
        }
        if (musicLength == 0) {
            music.close();
            gameMusic.close();
            gameMusic = new AudioStream(music);
            musicPlaying = false;
            musicLength = music.available();
        }
    }


    //TODO reached end;


}
