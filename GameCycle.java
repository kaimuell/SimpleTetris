import java.util.ArrayList;
import java.util.Random;

//Klasse zur Kontrolle des generellen Spielzyklus.
//VORSICHT muss mit einem Anzeigen Kontroller vor dem Start des Threads verbunden werden.

public class GameCycle extends Thread {
    private boolean[][] feld; //Feste Steine
    private Spielstein spielstein, naechsterSpielstein;
    private boolean spielende;
    private Random random;
    private int initPosX, initPosY;
    private int punkteStand;
    private int geschwindigkeit;
    private AnzeigenController anzeigenController;

    GameCycle( int initPosX, int initPosY, int anfangsgeschwindigkeit) {
        this.feld = new boolean[20][9];
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                feld[i][j] = false;
            }
        }
        spielende = true;
        this.random = new Random();
        this.initPosX = initPosX;
        this.initPosY = initPosY;
        this.punkteStand = 0;
        this.geschwindigkeit = anfangsgeschwindigkeit;
        erzeugeNaechstenSpielstein();
        this.spielstein = naechsterSpielstein;
        erzeugeNaechstenSpielstein();
    }
    public void addAnzeigenController(AnzeigenController anzeigenController){
        this.anzeigenController = anzeigenController;
    }

    public boolean isSpielende(){
        return spielende;
    }
    public Spielstein getNaechsterSpielstein() {
        return naechsterSpielstein;
    }

    public Spielstein getAktuellerSpielstein() {
        return spielstein;
    }

    public boolean[][] getFeld() {
        return feld;
    }

    public void erzeugeNaechstenSpielstein() {
        int zufallsZahl = (random.nextInt() % 4);
        switch (zufallsZahl) {
            case 0:
                naechsterSpielstein = new LForm(initPosX, initPosY, this);
                break;
            case 1:
                naechsterSpielstein = new Linie(initPosX, initPosY, this);
                break;
            case 2:
                naechsterSpielstein = new SForm(initPosX, initPosY, this);
                break;
            case 3:
                naechsterSpielstein = new Wuerfel(initPosX, initPosY, this);
                break;
            default:
                naechsterSpielstein = new Linie(initPosX, initPosY, this);
                break;
        }
    }

    public boolean kollisionsabfrage(Spielstein sp) {
        ArrayList<Pos2D> steinPositionen = new ArrayList<>(4);
        for (int i = 0; i < sp.getForm().length; i++) {
            for (int j = 0; j < sp.getForm()[i].length; j++) {
                if (sp.getForm()[i][j]) {
                    steinPositionen.add(new Pos2D((i + sp.getPosX()), (j + sp.getPosY())));
                }
            }
        }

        for (Pos2D p2D : steinPositionen) {
            if (p2D.getyPos() < 0 || p2D.getyPos() >= feld[0].length || p2D.getxPos() <= 0) {
                return true;
            }
        }

        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                if (feld[i][j]) {
                    for (Pos2D p2D : steinPositionen) {
                        if ((i == p2D.getxPos()) && (j == p2D.getyPos())) {
                            return true;

                        }
                    }
                }
            }
        }
        return false;
    }

    private void runde() {
        //falls spielstein in nächster xPosition Kollision zwischen feldern die true sind in feld und Spielstein,
        // oder Spielstein ist am Ende des Feldes
        if (kollisionsabfrage(new Spielstein((spielstein.getPosX() - 1), spielstein.getPosY(), spielstein.getForm(), this))) {
            //setze felder in feld true welche in spielstein true waren.
            //falls ein feld in oberster Reihe von feld==true : spielende = true
            for (int i = 0; i < spielstein.getForm().length; i++) {
                for (int j = 0; j < spielstein.getForm()[i].length; j++) {
                    if (spielstein.getForm()[i][j]) {
                        if (i + spielstein.getPosX() < feld.length) {
                            feld[i + spielstein.getPosX()][j + spielstein.getPosY()] = true;
                        } else spielende = true;
                    }
                }
            }

            //Lösche spielstein erzeuge neuen Spielstein
            spielstein = naechsterSpielstein;
            erzeugeNaechstenSpielstein();
            anzeigenController.updateVorschauCanvas();
        //falls Zeilen in feld vollständig gefüllt erhöhe Punktzahl, lösche die Zeilen und lasse die anderen nach unten fallen.
            int geloeschteZeilen = 0; //Zur Berechnung der Punktzahl;
            for (int i = 0; i < spielstein.getForm().length; i++) {
                int gefuellt = 0;
                for (int j = 0; j < spielstein.getForm()[i].length; j++) {
                    if (feld[i][j]) {
                        gefuellt += 1;
                    }
                }
                if (gefuellt == feld[i].length) {
                    geloeschteZeilen += 1;
                    for (int k = i; i < feld.length - 1; k++) {
                        for (int l = 0; k < feld[k].length; l++) {
                            feld[k][l] = feld[k + 1][l + 1];
                        }
                    }
                    for (int m = 0; m < feld[i].length; m++) {
                        feld[feld.length - 1][m] = false;
                    }
                }
            }
            this.punkteStand += (int) Math.pow(2, geloeschteZeilen);
            anzeigenController.updatePunkteLabel(punkteStand);
            anzeigenController.updateGameCanvas();
        }
        //sonst spielstein ein Feld nach unten bewegen.
        else {
            spielstein.PosXMinusEins();
            anzeigenController.updateGameCanvas();
        }
    }

    @Override
    public void run() {
        spielende = false;
        erzeugeNaechstenSpielstein();
        this.spielstein = naechsterSpielstein;
        erzeugeNaechstenSpielstein();
        if (anzeigenController != null){ anzeigenController.updateVorschauCanvas();}
        while (!spielende) {
            try {
                sleep(geschwindigkeit);
                runde();
                if (geschwindigkeit > 10) {
                    geschwindigkeit -= 1;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
