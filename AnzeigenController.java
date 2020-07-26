import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Klasse zur Kontrolle der sich verändernden Anzeigen
public class AnzeigenController {
    GameCanvas gameCanvas;
    VorschauCanvas vorschauCanvas;
    Label punkteLabel;
    GameCycle gameCycle;
    int XOFFSET = 300;
    Button startButton;

    AnzeigenController(){
        this.gameCycle = new GameCycle(this, 20, 4, 200);
        this.punkteLabel = new Label ("PUNKTE : 0");
        this.punkteLabel.setSize(XOFFSET, 40);
        this.punkteLabel.setLocation(XOFFSET, 20);
        this.punkteLabel.setVisible(true);
        this.vorschauCanvas = new VorschauCanvas(gameCycle.getNaechsterSpielstein());
        this.vorschauCanvas.setVisible(true);
        this.vorschauCanvas.setLocation(XOFFSET,120);
        this.gameCanvas = new GameCanvas(gameCycle);
        this.gameCanvas.setLocation(10, 10);
        this.gameCanvas.setVisible(true);
        this.startButton = createStartButton();
    }


    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    public VorschauCanvas getVorschauCanvas() {
        return vorschauCanvas;
    }

    public synchronized void updateVorschauCanvas(){
        this.vorschauCanvas.setSpielstein(gameCycle.getNaechsterSpielstein());
        this.vorschauCanvas.repaint();
    }

    private synchronized Button createStartButton(){
        Button startButton = new Button("START");
        startButton.setEnabled(true);
        startButton.setSize(100,30);
        startButton.setLocation(XOFFSET,400);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameCycle.isSpielende()) {
                    if (!gameCycle.wurdeGestartet) {
                        gameCycle.setSpielende(false);
                        gameCycle.start();
                        gameCanvas.requestFocus();
                        startButton.setEnabled(false);
                    }else{
                        gameCycle.setSpielende(false);
                        gameCanvas.requestFocus();
                        startButton.setEnabled(false);
                    }
                }
            }
        });
        return startButton;
    }

    public void resetStartButton(){
        this.startButton.setEnabled(true);
    }

    public synchronized void updateGameCanvas(){
        gameCanvas.repaint();
    }
    public synchronized void updatePunkteLabel (int punkte){
        String s = "PUNKTE: " +punkte;
        punkteLabel.setText(s);
    }

}
