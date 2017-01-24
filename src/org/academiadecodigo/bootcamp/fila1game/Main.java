package org.academiadecodigo.bootcamp.fila1game;


/**
 * Created by codecadet on 1/20/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game1 = new Game();

        int count = 0;

        while (true) {
            Thread.sleep(20);

            if (count < 550) {
                game1.start();
            }

            count++;
        }

    }

}
