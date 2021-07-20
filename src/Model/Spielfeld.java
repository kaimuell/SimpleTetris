package Model;

import Dienste.SpielsteinFactoryP.SpielsteinFactory;
import Entities.Spielstein;


public class Spielfeld implements ModelViewInterface{
    private boolean[][] feld; //Feste Steine
    private Spielstein spielstein, naechsterSpielstein;
    private boolean spielende;
    private int initPosX, initPosY;
    private int punkteStand;

    public Spielfeld(int hoehe, int breite) {
        this.initPosY = (breite-1) / 2;
        this.initPosX = hoehe;
        SpielsteinFactory sf = new SpielsteinFactory(initPosX, initPosY);
        initialisiereSpielfeld(hoehe, breite);
        this.spielstein = sf.erzeugeSpielstein();
        this.naechsterSpielstein = sf.erzeugeSpielstein();
        this.spielende = true;
        this.punkteStand = 0;
    }

    private void initialisiereSpielfeld(int hoehe, int breite) {
        this.feld = new boolean[hoehe][breite];
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                feld[i][j] = false;
            }
        }
    }


    public boolean[][] getFeld() {
        return feld;
    }

    public Spielstein getSpielstein() {
        return spielstein == null? new SpielsteinFactory(initPosX, initPosY).erzeugeSpielstein() : spielstein;
    }

    public void setSpielstein(Spielstein spielstein) {
        this.spielstein = spielstein;
    }

    public Spielstein getNaechsterSpielstein() {
        return naechsterSpielstein == null ? new SpielsteinFactory(initPosX, initPosY).erzeugeSpielstein() : naechsterSpielstein;
    }

    public void setNaechsterSpielstein(Spielstein naechsterSpielstein) {
        this.naechsterSpielstein = naechsterSpielstein;
    }

    public boolean isSpielende() {
        return spielende;
    }

    public void setSpielende(boolean spielende) {
        this.spielende = spielende;
    }

    public int getInitPosX() {
        return initPosX;
    }


    public int getInitPosY() {
        return initPosY;
    }


    public int getPunkteStand() {
        return punkteStand;
    }

    public void setPunkteStand(int punkteStand) {
        this.punkteStand = punkteStand;
    }


}
