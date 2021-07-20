package View;

import Controller.GameStateListener;
import Controller.Steuerung;
import Model.ModelViewInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Klasse zur Kontrolle der sich verändernden Anzeigen
public class AnzeigenController implements GameStateListener {
    private GameCanvas gameCanvas;
    private VorschauCanvas vorschauCanvas;
    private Label punkteLabel;
    private final int XOFFSET = 300;
    private Button startButton;
    private ModelViewInterface model;
    private Steuerung steuerung;

    public AnzeigenController(ModelViewInterface modelViewInterface, Steuerung steuerung){
        this.model = modelViewInterface;
        this.steuerung = steuerung;
        this.punkteLabel = new Label ("PUNKTE : 0");
        this.punkteLabel.setSize(XOFFSET, 40);
        this.punkteLabel.setLocation(XOFFSET, 20);
        this.punkteLabel.setVisible(true);
        this.vorschauCanvas = new VorschauCanvas(model.getNaechsterSpielstein());
        this.vorschauCanvas.setVisible(true);
        this.vorschauCanvas.setLocation(XOFFSET,120);
        this.gameCanvas = new GameCanvas(model, steuerung);
        this.gameCanvas.setLocation(30, 10);
        this.gameCanvas.setVisible(true);
        this.startButton = createStartButton();
    }

    Label getPunkteLabel() {
        return punkteLabel;
    }

    Button getStartButton() {
        return startButton;
    }

    GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    VorschauCanvas getVorschauCanvas() {
        return vorschauCanvas;
    }

    private void updateVorschauCanvas(){
        this.vorschauCanvas.setSpielstein(model.getNaechsterSpielstein());
        this.vorschauCanvas.repaint();
    }

    private synchronized Button createStartButton(){
        Button startButton = new Button("START");
        startButton.setEnabled(true);
        startButton.setSize(100,30);
        startButton.setLocation(XOFFSET,380);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.isSpielende()) {
                        steuerung.start();
                        startButton.setEnabled(false);
                        gameCanvas.requestFocus();
                }
            }
        });
        return startButton;
    }

    private void resetStartButton(){
        this.startButton.setEnabled(true);
    }

    private void updateGameCanvasBackground(){gameCanvas.createBackgroundImage();}
    private void updateGameCanvas(){
        gameCanvas.repaint();
    }
    private void updatePunkteLabel (int punkte){
        String s = "PUNKTE: " +punkte;
        punkteLabel.setText(s);
    }

    @Override
    public void update() {
        updateGameCanvasBackground();
        updateGameCanvas();
        updatePunkteLabel(model.getPunkteStand());
        updateVorschauCanvas();
        if(model.isSpielende()){
            resetStartButton();
        }
    }
}
