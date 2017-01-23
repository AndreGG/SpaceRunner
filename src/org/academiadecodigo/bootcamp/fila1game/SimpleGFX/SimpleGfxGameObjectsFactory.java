//package org.academiadecodigo.bootcamp.fila1game.SimpleGFX;
//
//import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
//import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjectsFactory;
//import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjectsType;
//
///**
// * Created by codecadet on 1/20/17.
// */
//public class SimpleGfxGameObjectsFactory implements GameObjectsFactory {
//
//
//    @Override
//    public SimpleGfxGameObjects getNewGO(GameObjectsType type) {
//        SimpleGfxGameObjects gameObject = null;
//
//        switch (type) {
//            case OBSTACLE1:
//                gameObject = new SimpleGfxObstacle1();
//                break;
//            case PICKUP:
//                gameObject = new SimpleGfxPickup();
//                break;
//
//        }
//
//        return gameObject;
//    }
//}
