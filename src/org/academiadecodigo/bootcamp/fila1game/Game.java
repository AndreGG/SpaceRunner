package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.*;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.Representables.Stage;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxObstacle1;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxStage;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.bootcamp.fila1game.NewAudio;
import java.lang.reflect.Array;
import java.util.List;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;


/**
 * Created by codecadet on 1/20/17.
 */
public class Game implements KeyboardHandler {

    private GameObjects[] gameObjects = new GameObjects[5];
    private GameObjects activeObject;
    private boolean menuPhase;
    private boolean playPhase;
    private boolean gameOver;
    private boolean menuChoice;
    private boolean gamePause;
    private Picture[] loadingScreen;
    private Picture[] menuScreen;
    private Picture[][] highscoresNumbers = new Picture[5][10];
    private Rectangle[] borders = new Rectangle[2];
    private menuOptions currentOption;
    private Keyboard keyboard;
    private Stage stage;
    private Player player;
    private CollisionChecker checker;
    private InputStream music;
    private AudioStream gameMusic;
    private boolean musicPlaying;
    private int musicLength;
    private int gameSpeed = -5;
    private int gameSpeedCount = 0;
    private double loadingCount = 0;
    private Integer highscore = 0;
    private int highscoreCounter = 0;
    String score = "00000";
    private SimpleGfxPlayer simplePlayer;

    // TODO private ActiveBlock;

    public Game() throws IOException {

        menuPhase = false;
        playPhase = false;
        gameOver = false;
        menuChoice = false;
        keyboard = new Keyboard(this);

        loadResources();

    }

    public void start() throws IOException {

//        checkMusicPlaying();

        if (!musicPlaying) {
            music();
        }

        activeObject();

        menu(currentOption);

        if (!menuPhase) {
            if (!simplePlayer.isPlayerDead()) {
                menuScreen[6].delete();
                if (!gamePause) {
                    menuScreen[7].delete();
                    trackHighScore();
                    showHighScore();

                    checker.setActiveObject(activeObject);

                    playGame();

                } else {
                    menuScreen[7].draw();
                }
            } else {
                menuScreen[6].draw();

            }
        }

    }

    private void activeObject() {
        for (int i = 0; i < gameObjects.length; i++) {
            if (gameObjects[i].getObject().isActive() == true) {
                return;
            }
        }

        activeObject = gameObjects[(int) (Math.random() * gameObjects.length)];
        activeObject.getObject().setActive(true);

    }

    public void keyboardInit() {

        /**
         * SPACE KEY
         */

        KeyboardEvent chooseMenuAction = new KeyboardEvent();
        chooseMenuAction.setKey(KeyboardEvent.KEY_SPACE);
        chooseMenuAction.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(chooseMenuAction);

        KeyboardEvent releaseMenuAction = new KeyboardEvent();
        releaseMenuAction.setKey(KeyboardEvent.KEY_SPACE);
        releaseMenuAction.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(releaseMenuAction);

        /**
         * DOWN KEY
         */

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        /**
         * UP KEY
         */

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        /**
         * Q KEY
         */

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quit);

        /**
         * P KEY
         */

        KeyboardEvent pause = new KeyboardEvent();
        pause.setKey(KeyboardEvent.KEY_P);
        pause.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pause);

        /** R KEY
         *
         */

        KeyboardEvent restart = new KeyboardEvent();
        restart.setKey(KeyboardEvent.KEY_R);
        restart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(restart);

    }

    private void loadResources() throws IOException {

        /**
         * Loading screen assets
         */

        loadingScreen = new Picture[4];

        for (int i = 0; i < loadingScreen.length; i++) {
            loadingScreen[i] = new Picture(10, 10, "/loading/loading_" + i + ".png");
        }

        loadingScreen[0].draw();

        while (loadingCount < 99999999 * 4) {
            loadingCount++;
        }

        /**
         * Sound assets
         */

        NewAudio.load(new String[]{"/Music/Fila1Theme", "/Music/jump", "/Music/stand_obstacle"});

        /**
         * Gfx assets and Collision Assignment
         */

        stage = new Stage(new SimpleGfxStage());


        for (int i = 0; i < highscoresNumbers.length; i++) {
            for (int j = 0; j < highscoresNumbers[i].length; j++) {
                highscoresNumbers[i][j] = new Picture(875, 30, "/highscores/" + j + ".png");
            }
        }


        menuScreen = new Picture[8];

        for (int i = 0; i < menuScreen.length; i++) {
            menuScreen[i] = new Picture(10, 10, "/menu/menu_" + i + ".png");
        }

        loadingScreen[1].draw();
        loadingScreen[0].delete();

        while (loadingCount < 99999999 * 6) {
            loadingCount++;
        }

        checker = new CollisionChecker();
        player = new Player(new SimpleGfxPlayer(70, 500, checker));
        player.setChecker(checker);

        gameObjects[0] = new Obstacle1(new SimpleGfxObstacle1(934, 480));
        gameObjects[1] = new Obstacle2(new SimpleGfxObstacle2(934, 480));
        gameObjects[2] = new Obstacle3(new SimpleGfxObstacle3(934, 480));
        gameObjects[3] = new Obstacle4(new SimpleGfxObstacle4(934, 480));
        gameObjects[4] = new Obstacle5(new SimpleGfxObstacle5(934, 480));

        /**
         * Game variables set up and keyboard
         */

        currentOption = menuOptions.START;

        menuPhase = true;
        gamePause = false;

        loadingScreen[2].draw();
        loadingScreen[1].delete();

        while (loadingCount < 99999999 * 8) {
            loadingCount++;
        }

        keyboardInit();

        loadingScreen[3].draw();

        while (loadingCount < 99999999 * 10) {
            loadingCount++;
        }

        for (Picture load : loadingScreen) {
            load.delete();
        }

        simplePlayer = (SimpleGfxPlayer) player.getSprite();

    }

    private void highlightMenuChoice(int x) {

        menuScreen[x].draw();

        for (int i = 0; i < menuScreen.length; i++) {
            if (i == x) {
                continue;
            } else {
                menuScreen[i].delete();
            }
        }

    }

    private void menu(menuOptions option) {

        int x = -1;

        if (menuPhase) {

            switch (option) {
                case START:
                    x = 0;
                    highlightMenuChoice(x);

                    if (menuChoice) {

                        menuPhase = false;
                        playPhase = true;

                        for (Picture menu : menuScreen) {
                            menu.delete();
                        }

                        stage.show();

                        player.getSprite().show();
                        for (GameObjects object : gameObjects) {
                            object.getObject().show();
                        }


                        for (int i = 0; i < highscoresNumbers.length; i++) {
                            for (int j = 0; j < highscoresNumbers[i].length; j++) {
                                highscoresNumbers[i][j].translate(-40 * i, 0);
                            }
                        }

                        loadEdges();
                    }
                    break;
                case INSTRUCTIONS:
                    x = 1;
                    highlightMenuChoice(x);

                    if (menuChoice) {
                        x = 5;
                        highlightMenuChoice(x);
                    }


                    break;
                case CREDITS:
                    x = 2;
                    highlightMenuChoice(x);

                    if (menuChoice) {
                        x = 4;
                        highlightMenuChoice(x);
                    }
                    break;
                case EXIT:
                    x = 3;
                    highlightMenuChoice(x);

                    if (menuChoice) {
                        System.exit(1);
                    }
                    break;
            }

        }

    }

    private void loadEdges() {

        // BORDAS PRETAS AQUI PARA SEREM CRIADAS DEPOIS
        borders[0] = new Rectangle(10, 10, 100, 576);
        borders[0].setColor(Color.BLACK);
        borders[0].fill();

        borders[1] = new Rectangle(1024 - 90, 10, 110, 576);
        borders[1].setColor(Color.BLACK);
        borders[1].fill();
        borders[1].fill();

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

        NewAudio.start("/Music/Fila1Theme");

    }

    private void checkMusicPlaying() throws IOException {

        if (musicLength != 0 && musicPlaying) {
            musicLength = music.available();
        }

        if (musicLength == 0) {
        }

    }

    public menuOptions menuDown(menuOptions option) {

        switch (option) {
            case START:
                currentOption = menuOptions.INSTRUCTIONS;
                break;
            case INSTRUCTIONS:
                currentOption = menuOptions.CREDITS;
                break;
            case CREDITS:
                currentOption = menuOptions.EXIT;
                break;
            case EXIT:
                currentOption = menuOptions.START;
                break;
        }

        return currentOption;
    }

    public menuOptions menuUp(menuOptions option) {

        switch (option) {
            case START:
                currentOption = menuOptions.EXIT;
                break;
            case INSTRUCTIONS:
                currentOption = menuOptions.START;
                break;
            case CREDITS:
                currentOption = menuOptions.INSTRUCTIONS;
                break;
            case EXIT:
                currentOption = menuOptions.CREDITS;
                break;
        }

        return currentOption;
    }

    private enum menuOptions {
        START,
        INSTRUCTIONS,
        CREDITS,
        EXIT;
    }

    @Override
    public void keyPressed(KeyboardEvent key) {
        switch (key.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                menuChoice = true;
                break;
            case KeyboardEvent.KEY_DOWN:
                menuDown(currentOption);
                break;
            case KeyboardEvent.KEY_UP:
                menuUp(currentOption);
                break;
            case KeyboardEvent.KEY_Q:
                if (playPhase) {
                    System.exit(1);
                }
                break;
            case KeyboardEvent.KEY_P:
                gamePause = !gamePause;
                break;
            case KeyboardEvent.KEY_R:
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent key) {
        switch (key.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                menuChoice = false;
                break;
        }

    }

    private void gameSpeed() {
        if (gameSpeed > -30){
            gameSpeedCount++;

            if (gameSpeedCount == 200){
                gameSpeed--;
                gameSpeedCount = 0;
            }
        }
    }

    private void playGame() {

        gameSpeed();

        stage.getStage().move(gameSpeed);

        for (GameObjects object: gameObjects) {
            object.getObject().move(gameSpeed);
        }

        player.move();

    }

    private void trackHighScore() {

        highscoreCounter++;

        if (highscoreCounter == 10) {
            highscore += 100;
            highscoreCounter = 0;
        }
    }

    private void showHighScore() {

        score = highscore.toString();

        int digitPosition = 0;

        for (int j = score.length() - 1; j > -1; j--) {

            int character1 = Character.getNumericValue(score.charAt(j));

            for (int i = 0; i < highscoresNumbers[digitPosition].length; i++) {
                if (i == character1) {
                    highscoresNumbers[digitPosition][i].draw();
                    continue;
                }
                highscoresNumbers[digitPosition][i].delete();

            }
            digitPosition++;
            if (digitPosition == 5) {
                digitPosition = 0;
            }

        }
    }

    public void gameRestart() throws IOException {
        if (simplePlayer.isPlayerDead()) {
            playPhase = false;
            gameOver = false;
            menuChoice = false;
            simplePlayer.setAsAlive();
            loadResources();
        }

    }

}





