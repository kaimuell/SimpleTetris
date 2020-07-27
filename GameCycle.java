import java.util.ArrayList;
import java.util.Random;

//Klasse zur Kontrolle des generellen Spielzyklus.

public class GameCycle extends Thread {
    private boolean[][] feld; //Feste Steine
    private Spielstein spielstein, naechsterSpielstein;
    private boolean spielende;
    private Random random;
    private int initPosX, initPosY;
    private int punkteStand;
    private int geschwindigkeit;
    private AnzeigenController anzeigenController;
    public boolean wurdeGestartet;
    private boolean aktuellesSpielAbgeschlossen;

    GameCycle(AnzeigenController anzeigenController, int initPosX, int initPosY, int anfangsgeschwindigkeit) {
        this.feld = new boolean[20][10];
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
        this.anzeigenController = anzeigenController;
        this.aktuellesSpielAbgeschlossen = false;
    }

    public void resetGameCycle(){
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                feld[i][j] = false;
            }
        }

        this.punkteStand = 0;
        erzeugeNaechstenSpielstein();
        this.spielstein = naechsterSpielstein;
        erzeugeNaechstenSpielstein();
    }

    public boolean isSpielende(){
        return spielende;
    }

    public void setSpielende (boolean b){
        this.spielende = b;
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
        int zufallsZahl = Math.abs(random.nextInt() % 6);
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
            case 4:
                naechsterSpielstein = new SFormGespiegelt(initPosX, initPosY, this);
                break;
            case 5:
                naechsterSpielstein = new LFormGespiegelt(initPosX, initPosY, this);
                break;
            default:
                naechsterSpielstein = new Linie(initPosX, initPosY, this);
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

    protected void runde() {
        //falls spielstein in nächster xPosition Kollision zwischen feldern die true sind in feld und Spielstein,
        // oder Spielstein ist am Ende des Feldes
        if (!isSpielende()) {
            if (kollisionsabfrage(new Spielstein((spielstein.getPosX() - 1), spielstein.getPosY(), spielstein.getForm(), this))) {
                //setze felder in feld true welche in spielstein true waren.
                //falls ein feld in oberster Reihe von feld==true : spielende = true
                for (int i = 0; i < spielstein.getForm().length; i++) {
                    for (int j = 0; j < spielstein.getForm()[i].length; j++) {
                        if (spielstein.getForm()[i][j]) {
                            if (i + spielstein.getPosX() < feld.length) {
                                feld[i + spielstein.getPosX()][j + spielstein.getPosY()] = true;
                            } else {
                                spielende = true;
                                aktuellesSpielAbgeschlossen = true;
                            }
                        }
                    }
                }

                //Lösche spielstein erzeuge neuen Spielstein
                spielstein = naechsterSpielstein;
                erzeugeNaechstenSpielstein();
                anzeigenController.updateVorschauCanvas();
                //falls Zeilen in feld vollständig gefüllt erhöhe Punktzahl, lösche die Zeilen und lasse die anderen nach unten fallen.
                int geloeschteZeilen = 0; //Zur Berechnung der Punktzahl;
                for (int i = 0; i < feld.length; i++) {
                    int gefuellt = 0;
                    for (int j = 0; j < feld[i].length; j++) {
                        if (feld[i][j]) {
                            gefuellt += 1;
                        }
                    }
                    if (gefuellt == feld[i].length) {
                        int iTemp = i;
                        i = i - 1; //Um aktuelles Feld nocheinmal zu durchlaufen
                        geloeschteZeilen += 1;
                        for (int k = iTemp; k < feld.length - 1; k++) {
                            for (int l = 0; l < feld[k].length; l++) {
                                feld[k][l] = feld[k + 1][l];
                            }
                        }
                        for (int m = 0; m < feld[iTemp].length; m++) {
                            feld[feld.length - 1][m] = false;
                        }
                    }
                }
                if (geloeschteZeilen > 0) {
                    this.punkteStand += (int) Math.pow(2, geloeschteZeilen);
                }
                anzeigenController.updatePunkteLabel(punkteStand);
                anzeigenController.updateGameCanvasBackground();
                anzeigenController.updateGameCanvas();
            }
            //sonst spielstein ein Feld nach unten bewegen.
            else {
                spielstein.PosXMinusEins();
                anzeigenController.updateGameCanvas();
            }
        }
    }

    @Override
    public synchronized void run() {
        wurdeGestartet = true;
        erzeugeNaechstenSpielstein();
        this.spielstein = naechsterSpielstein;
        erzeugeNaechstenSpielstein();
        if (anzeigenController != null) {
            anzeigenController.updateVorschauCanvas();
        }
        while (true) {

            //Spiel ausführen
            while (!isSpielende()) {
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
            // Spiel zurücksetzen.
            if (aktuellesSpielAbgeschlossen) {
                resetGameCycle();
                anzeigenController.resetStartButton();
                anzeigenController.gameCanvas.repaint();
                aktuellesSpielAbgeschlossen = false;

            }

            try {
                sleep(1000);
               /* if (geschwindigkeit > 10) {
                    geschwindigkeit -= 1;
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
