package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

import java.util.Random;

public class SpielsteinFactory {
    private final int initPosX;
    private final int initPosY;
    Random random;

    public SpielsteinFactory(int initPosX, int initPosY) {
        this.initPosX = initPosX;
        this.initPosY = initPosY;
        this.random = new Random();
    }

    public Spielstein erzeugeSpielstein(){
        int zufallsZahl = Math.abs(random.nextInt() % 6);
        switch (zufallsZahl) {
            case 0:
                return new LForm(initPosX, initPosY);
            case 1:
                return new Linie(initPosX, initPosY);
            case 2:
                return new SForm(initPosX, initPosY);
            case 3:
                return new Wuerfel(initPosX, initPosY);
            case 4:
                return new SFormGespiegelt(initPosX, initPosY);
            case 5:
                return new LFormGespiegelt(initPosX, initPosY);
            default:
                return new Linie(initPosX, initPosY);
        }
    }
}
