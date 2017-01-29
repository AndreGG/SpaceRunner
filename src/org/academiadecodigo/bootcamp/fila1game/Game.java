package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle2;
import org.academiadecodigo.bootcamp.fila1game.Representables.MovableRepresentable;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.Representables.Stage;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxObstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxObstacle2;
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

    private GameObjects[] gameObjects = new GameObjects[2];
    private GameObjects activeObject;
    private boolean menuPhase;
    private boolean playPhase;
    private boolean gameoverPhase;
    private boolean menuChoice;
    private Picture[] loadingScreen;
    private Picture[] menuScreen;
    private menuOptions currentOption;
    private Keyboard keyboard;
    private Stage stage;
    private Player player;
    private Obstacle1 obstacle1;
    private CollisionChecker checker;
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

        loadResources();
        System.out.println(menuOptions.values()[0]);
        System.out.println(menuOptions.values()[1]);
        System.out.println(menuOptions.values()[2]);
        System.out.println(menuOptions.values()[3]);

    }

    public void start() throws IOException {

        checkMusicPlaying();

        if (!musicPlaying) {
            music();
        }

        activeObject();
        checker.setActiveObject(activeObject);

        for (GameObjects object: gameObjects) {
            object.getObject().move();
        }

        stage.move();
        player.move();

    }


    private void activeObject() {
        for (int i = 0; i < gameObjects.length; i++) {
            if (gameObjects[i].getObject().isActive() == true) {
                return;
            }
        }

        activeObject = gameObjects[(int) (Math.random() * gameObjects.length)];
//        activeObject = gameObjects[1];
        activeObject.getObject().setActive(true);

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

    private void loadResources() throws IOException {

        loadingScreen = new Picture[4];

        for (int i = 0; i < loadingScreen.length; i++) {
            loadingScreen[i] = new Picture(10, 10, "/loading/loading_" + i + ".png");
        }

        loadingScreen[0].draw();

        keyboardInit();

        stage = new Stage(new SimpleGfxStage());
        gameObjects[0] = new Obstacle1(new SimpleGfxObstacle1(934, 500));
        gameObjects[1] = new Obstacle2(new SimpleGfxObstacle2(934, 500));

        menuScreen = new Picture[4];

        for (int i = 0; i < menuScreen.length; i++) {
            menuScreen[i] = new Picture(10, 10, "/menu/menu_" + i + ".png");
        }

        loadingScreen[1].draw();
        loadingScreen[0].delete();

        checker = new CollisionChecker();
        player = new Player(new SimpleGfxPlayer(70, 500, checker));
        player.setChecker(checker);

        currentOption = menuOptions.START;

        loadingScreen[2].draw();
        loadingScreen[1].delete();

        stage.show();
        player.getSprite().show();

        loadingScreen[3].draw();

        musicLength = music.available();

        menuPhase = true;

        for (Picture load : loadingScreen) {
            load.delete();
        }

    }

    private void menu(menuOptions option) {

        currentOption = option;

        if (menuPhase) {

            menuScreen[0].draw();

            switch (currentOption) {
                case START:
                    menuScreen[0].draw();

                    System.out.println("Option Start");

                    if (menuChoice) {

                        menuPhase = false;
                        for (Picture menu : menuScreen) {
                            menu.delete();
                        }

                    }
                    break;
                case INSTRUCTIONS:
                    menuScreen[1].draw();

                    System.out.println("Option Instructions");

                    if (menuChoice) {

                    }
                    break;
                case CREDITS:
                    menuScreen[2].draw();

                    System.out.println("Credits Instructions");
                case EXIT:
                    menuScreen[3].draw();

                    System.out.println("Option Exit");

                    if (menuChoice) {
                        System.exit(1);
                    }
                    break;
            }

        }

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

        //System.out.println(AudioPlayer.activeCount());

    }

    private void checkMusicPlaying() throws IOException {

        if (musicLength != 0 && musicPlaying) {
            musicLength = music.available();
        }

        if (musicLength == 0) {
            music.close();
            gameMusic.close();
            music = new FileInputStream("Resources/Music/SpaceRun.wav");
            gameMusic = new AudioStream(music);
            musicPlaying = false;
            musicLength = music.available();
        }

    }

    //TODO reached end;

    private enum menuOptions {
        START,
        INSTRUCTIONS,
        CREDITS,
        EXIT;
    }

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


}


