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

/**
 * Created by codecadet on 1/20/17.
 */
public class Game implements KeyboardHandler{

    private GameObjects[] gameObjects;
    private Keyboard keyboard = new Keyboard(this);
    private Stage stage;
    private Player player;
    private Obstacle1 obstacle1;
    private boolean menuPhase;
    private boolean playPhase;
    private boolean gameoverPhase;
    private boolean menuChoice;
    private Picture loadingScreen;
    private menuOptions currentOption;
    private List<menuOptions> currentOptions;

    // TODO private ActiveBlock;

    public Game() {

        menuPhase = false;
        playPhase = false;
        gameoverPhase = false;
        menuChoice = false;

        loading();

    }

    public void start() {

        obstacle1.move();
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

    private void loading() {

        loadingScreen = new Picture(10, 10, "loading.jpg");
        loadingScreen.draw();

        keyboardInit();

        player = new Player(new SimpleGfxPlayer(70, 500));
        obstacle1 = new Obstacle1(new SimpleGfxObstacle1(934, 500));
        stage = new Stage(new SimpleGfxStage());

        CollisionChecker checker = new CollisionChecker(obstacle1);
        player.setChecker(checker);

        currentOption = menuOptions.START;

        stage.show();
        player.getSprite().show();

        menuPhase = true;

    }

    private void menu(menuOptions option) {

        currentOption = option;

        if (menuPhase) {

            menuPicture.show();
            menuChoices[0].show;

            switch (option) {
                case START:
                    menuChoices[0].show;

                    System.out.println("Option Start");

                    if (menuChoice) {
                        menuPhase = false;
                    }
                    break;
                case INSTRUCTIONS:
                    menuChoices[1].show;

                    System.out.println("Option Instructions");

                    if (menuChoice) {

                    }
                    break;
                case EXIT:
                    menuChoices[2].show;

                    System.out.println("Option Exit");

                    if (menuChoice) {
                        System.exit(1);
                    }
                    break;
            }

        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyPressed) {
        switch (keyPressed.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                method(keyPressed.getKey());
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


    //TODO reached end;

    private enum menuOptions {
        START,
        INSTRUCTIONS,
        EXIT;
    }

}


