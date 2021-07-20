package Controller;

import Model.Spielfeld;

import java.util.ArrayList;
import java.util.List;

public class TetrisController implements Steuerung{
    private Spielfeld spielfeld;
    private SpielsteinController spielsteinController;
    private SpielRunde runde;
    private List<GameStateListener> gameStateListeners;

    public TetrisController(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
        this.spielsteinController = new SpielsteinController(spielfeld);
        this.gameStateListeners = new ArrayList<>(2);

    }

    public void add(GameStateListener gameStateListener){
        gameStateListeners.add(gameStateListener);
    }

    @Override
    public void start() {
        spielfeld.setSpielende(false);
        this.runde = new SpielRunde(spielfeld, spielsteinController, gameStateListeners, 200);
        this.runde.start();
    }

    @Override
    public void linksBewegen() {
        spielsteinController.linksBewegen();
    }

    @Override
    public void rechtsBewegen() {
        spielsteinController.rechtsBewegen();
    }

    @Override
    public void naechteRunde() {
        runde.runde();
    }

    @Override
    public void rotieren() {
        if (spielsteinController != null) {
            spielsteinController.rotieren();
        }
    }
}
