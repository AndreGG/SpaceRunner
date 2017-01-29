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
    private Picture[] loadingScreen;
    private Picture[] menuScreen;
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

        loadResources();

    }

    public void start() throws IOException {

        checkMusicPlaying();

        if (!musicPlaying) {
            music();
        }

        menu(currentOption);

        if (!menuPhase) {
            obstacle1.move();
            stage.move();
            player.move();
        }

    }

    public void keyboardInit() {

        KeyboardEvent chooseMenuAction = new KeyboardEvent();
        chooseMenuAction.setKey(KeyboardEvent.KEY_SPACE);
        chooseMenuAction.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(chooseMenuAction);

        KeyboardEvent releaseMenuAction = new KeyboardEvent();
        releaseMenuAction.setKey(KeyboardEvent.KEY_SPACE);
        releaseMenuAction.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(releaseMenuAction);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
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
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934, 500));

        menuScreen = new Picture[4];

        for (int i = 0; i < menuScreen.length; i++) {
            menuScreen[i] = new Picture(10, 10, "/menu/menu_" + i + ".png");
        }

        loadingScreen[1].draw();
        loadingScreen[0].delete();

        CollisionChecker checker = new CollisionChecker(obstacle1);
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

        for (Picture load: loadingScreen) {
            load.delete();
        }

    }

    private void menu(menuOptions option) {

        if (menuPhase) {

            switch (option) {
                case START:
                    menuScreen[0].draw();

                    System.out.println("Option Start");

                    if (menuChoice) {

                        menuPhase = false;

                        for (Picture menu: menuScreen) {
                            menu.delete();
                        }

                    }
                    break;
                case INSTRUCTIONS:
                    menuScreen[1].draw();

                    menuScreen[0].delete();
                    menuScreen[2].delete();

                    System.out.println("Option Instructions");

                    if (menuChoice) {

                    }
                    break;
                case CREDITS:
                    menuScreen[2].draw();

                    menuScreen[1].delete();
                    menuScreen[3].delete();

                    System.out.println("Credits Instructions");

                    break;
                case EXIT:
                    menuScreen[3].draw();

                    menuScreen[0].delete();
                    menuScreen[2].delete();

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

        System.out.println(AudioPlayer.activeCount());

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
    //TODO reached end;

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
                System.out.println("button");
                break;
            case KeyboardEvent.KEY_UP:
                menuUp(currentOption);
                System.out.println("button2");
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


}


