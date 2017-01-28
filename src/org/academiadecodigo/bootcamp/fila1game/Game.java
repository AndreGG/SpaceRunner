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
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.List;
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
public class Game implements KeyboardHandler {

    private GameObjects[] gameObjects;
    private boolean menuPhase;
    private boolean playPhase;
    private boolean gameoverPhase;
    private boolean menuChoice;
    private Picture loadingScreen;
    private menuOptions currentOption;
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

        menuPhase = false;
        playPhase = false;
        gameoverPhase = false;
        menuChoice = false;
        keyboard = new Keyboard(this);

        loading();

        stage = new Stage(new SimpleGfxStage());
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934, 500));
        CollisionChecker checker = new CollisionChecker(obstacle1);
        player = new Player(new SimpleGfxPlayer(70, 500, checker));
        musicLength = music.available();

    }

    public void start() throws IOException {

        checkMusicPlaying();

        if (!musicPlaying) {
            music();
        }

       obstacle1.move();
       stage.move();
       player.move();

    }

    public void keyboardInit() {

        KeyboardEvent chooseMenuAction = new KeyboardEvent();
        chooseMenuAction.setKey(KeyboardEvent.KEY_SPACE);
        chooseMenuAction.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(chooseMenuAction);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(up);

    }

    private void loading() throws IOException {

        loadingScreen = new Picture(10, 10, "loading.jpg");
        loadingScreen.draw();

        keyboardInit();


        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934, 500));
        stage = new Stage(new SimpleGfxStage());

        CollisionChecker checker = new CollisionChecker(obstacle1);
        player = new Player(new SimpleGfxPlayer(70, 500, checker));
        player.setChecker(checker);

        currentOption = menuOptions.START;

        stage.show();
        player.getSprite().show();

        menuPhase = true;

    }

    //TODO
//    private void menu(menuOptions option) {
//
//        currentOption = option;
//
//        if (menuPhase) {
//
//            menuPicture.show();
//            menuChoices[0].show;
//
//            switch (option) {
//                case START:
//                    menuChoices[0].show;
//
//                    System.out.println("Option Start");
//
//                    if (menuChoice) {
//                        menuPhase = false;
//                    }
//                    break;
//                case INSTRUCTIONS:
//                    menuChoices[1].show;
//
//                    System.out.println("Option Instructions");
//
//                    if (menuChoice) {
//
//                    }
//                    break;
//                case EXIT:
//                    menuChoices[2].show;
//
//                    System.out.println("Option Exit");
//
//                    if (menuChoice) {
//                        System.exit(1);
//                    }
//                    break;
//            }
//
//        }
//
//    }

    @Override
    public void keyPressed(KeyboardEvent keyPressed) {
        switch (keyPressed.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                // TODO method(keyPressed.getKey());
                break;
            case KeyboardEvent.KEY_DOWN:
                break;
            case KeyboardEvent.KEY_UP:
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


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

        System.out.println(AudioPlayer.activeCount());

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

    private enum menuOptions {
        START,
        INSTRUCTIONS,
        EXIT;
    }

}


