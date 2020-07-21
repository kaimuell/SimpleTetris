import java.awt.*;

//Klasse zur Kontrolle der sich verändernden Anzeigen
public class AnzeigenController {
    GameCanvas gameCanvas;
    VorschauCanvas vorschauCanvas;
    Label punkteLabel;
    GameCycle gameCycle;
    int XOFFSET = 300;

    AnzeigenController(GameCycle gameCycle){
        this.gameCycle = gameCycle;
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

    public synchronized void updateGameCanvas(){
        gameCanvas.repaint();
    }
    public synchronized void updatePunkteLabel (int punkte){
        String s = "PUNKTE: " +punkte;
        punkteLabel.setText(s);
    }

}
