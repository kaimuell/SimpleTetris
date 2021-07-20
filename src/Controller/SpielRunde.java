package Controller;

import Entities.Spielstein;
import Model.Spielfeld;

import java.util.List;

//Klasse zur Kontrolle des generellen Spielzyklus.

public class SpielRunde extends Thread {
    private Spielfeld spielfeld;
    private List<GameStateListener> gameStateListeners;
    SpielsteinController spielsteinController;

    private int geschwindigkeit;


    SpielRunde(Spielfeld spielfeld, SpielsteinController spielsteinController, List<GameStateListener> gameStateListeners, int anfangsgeschwindigkeit) {
        this.spielfeld = spielfeld;
        this.geschwindigkeit = anfangsgeschwindigkeit;
        this.spielsteinController = spielsteinController;
        this.gameStateListeners = gameStateListeners;
        erzeugeNaechstenSpielstein();
        spielfeld.setSpielende(false);
        resetSpielfeld();
        updateGameStateListeners();
    }

    public void resetSpielfeld(){
        for (int i = 0; i < spielfeld.getFeld().length; i++) {
            for (int j = 0; j < spielfeld.getFeld()[i].length; j++) {
                spielfeld.getFeld()[i][j] = false;
            }
        }
        spielfeld.setPunkteStand(0);
        erzeugeNaechstenSpielstein();
    }

    private void erzeugeNaechstenSpielstein() {
        spielfeld.setSpielstein(spielfeld.getNaechsterSpielstein());
        spielfeld.setNaechsterSpielstein(spielsteinController.erzeugeNaechstenSpielstein());
    }


    protected void runde() {
        //falls spielstein in nächster xPosition Kollision zwischen feldern die true sind in feld und Entities.Spielstein,
        // oder Entities.Spielstein ist am Ende des Feldes
        if (!spielfeld.isSpielende()) {
            Spielstein spielstein = spielfeld.getSpielstein();
            if (spielsteinController.kollisionsabfrage(new Spielstein((spielstein.getPosX() - 1), spielstein.getPosY(), spielstein.getForm()))) {
                behandleKollision(spielstein);
            }
            //sonst spielstein ein Feld nach unten bewegen.
            else {
                spielfeld.getSpielstein().setPosX(spielfeld.getSpielstein().getPosX()-1);
                updateGameStateListeners();
            }
        }
    }

    private void behandleKollision(Spielstein spielstein) {
        //setze felder in feld true welche in spielstein true waren.
        //falls ein feld in oberster Reihe von feld==true : spielende = true
        ermittleObSpielende(spielstein);

        //Lösche spielstein erzeuge neuen Entities.Spielstein
        erzeugeNaechstenSpielstein();
        updateGameStateListeners();
        //falls Zeilen in feld vollständig gefüllt erhöhe Punktzahl, lösche die Zeilen und lasse die anderen nach unten fallen.
        int geloeschteZeilen = loescheZeilen();
        if (geloeschteZeilen > 0) {
            spielfeld.setPunkteStand(spielfeld.getPunkteStand() + (int) Math.pow(2, geloeschteZeilen));
        }
        updateGameStateListeners();
    }

    private void ermittleObSpielende(Spielstein spielstein) {
        for (int i = 0; i < spielstein.getForm().length; i++) {
                            for (int j = 0; j < spielstein.getForm()[i].length; j++) {
                                if (spielstein.getForm()[i][j]) {
                                    if (i + spielstein.getPosX() < spielfeld.getFeld().length) {
                                        spielfeld.getFeld()[i + spielstein.getPosX()][j + spielstein.getPosY()] = true;
                                    } else {
                                        spielfeld.setSpielende(true);
                    }
                }
            }
        }
    }

    private int loescheZeilen() {
        int geloeschteZeilen = 0; //Zur Berechnung der Punktzahl;
        for (int i = 0; i < spielfeld.getFeld().length; i++) {
            int gefuellt = ermittleZahlDerVollenKaestchenInFeld(i);
            if (gefuellt == spielfeld.getFeld()[i].length) {
                int iTemp = i;
                i = i - 1; //Um aktuelles Feld nocheinmal zu durchlaufen
                geloeschteZeilen += 1;
                loescheZeileundLasseZeilennachUntenFallen(iTemp);
            }
        }
        return geloeschteZeilen;
    }

    private void loescheZeileundLasseZeilennachUntenFallen(int iTemp) {
        for (int k = iTemp; k < spielfeld.getFeld().length - 1; k++) {
            for (int l = 0; l < spielfeld.getFeld()[k].length; l++) {
                spielfeld.getFeld()[k][l] = spielfeld.getFeld()[k + 1][l];
            }
        }
        for (int m = 0; m < spielfeld.getFeld()[iTemp].length; m++) {
            spielfeld.getFeld()[spielfeld.getFeld().length - 1][m] = false;
        }
    }

    private int ermittleZahlDerVollenKaestchenInFeld(int i) {
        int gefuellt = 0;
        for (int j = 0; j < spielfeld.getFeld()[i].length; j++) {
            if (spielfeld.getFeld()[i][j]) {
                gefuellt += 1;
            }
        }
        return gefuellt;
    }

    @Override
    public synchronized void run() {
        erzeugeNaechstenSpielstein();
        erzeugeNaechstenSpielstein();
        updateGameStateListeners();
            //Spiel ausführen
            while (!spielfeld.isSpielende()) {
                try {
                    sleep(geschwindigkeit);
                    runde();
               /* if (geschwindigkeit > 10) {
                    geschwindigkeit -= 1;
                }*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        updateGameStateListeners();
    }


    private void updateGameStateListeners() {
        for (GameStateListener gsl : gameStateListeners) {
            gsl.update();
        }
    }
}
