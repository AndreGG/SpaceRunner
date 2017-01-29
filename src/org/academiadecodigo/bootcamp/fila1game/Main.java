package org.academiadecodigo.bootcamp.fila1game;


import java.io.IOException;

/**
 * Created by codecadet on 1/20/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        Game game1 = new Game();

        while (true) {
            Thread.sleep(17);
                game1.start();
        }

    }

}
