import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//Canvas zur Anzeige des laufenden Spiels.
public class GameCanvas extends Canvas implements KeyListener {
    double breiteStein;
    double laengeStein;
    GameCycle gameCycle;


    GameCanvas(GameCycle gameCycle){
        this.gameCycle = gameCycle;
        this.setSize(180, 400);
        breiteStein =  ((this.getWidth()-1) / (gameCycle.getFeld()[1].length));
        laengeStein =  ((this.getHeight()-1) /(gameCycle.getFeld().length));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
     }


    private int yPos(int x){
        return (int) ((x * breiteStein));
    }

    private int xPos (int y){
        return (int) (this.getHeight() - (y* laengeStein));
    }

    @Override
    public void paint(Graphics g){
        //Male alle Punkte aus aktuellem Spielstein als Rechtecke.

        for (int spX=0;spX < gameCycle.getAktuellerSpielstein().getForm().length; spX ++){
            for (int spY=0; spY < gameCycle.getAktuellerSpielstein().getForm()[0].length; spY++){
            if ((spX + gameCycle.getAktuellerSpielstein().getPosX()) < gameCycle.getFeld().length){
                if (gameCycle.getAktuellerSpielstein().getForm()[spX][spY]){
                    g.setColor(Color.RED);
                    g.drawRect(yPos(gameCycle.getAktuellerSpielstein().getPosY()+spY), xPos(gameCycle.getAktuellerSpielstein().getPosX()+spX),
                             (int) breiteStein, (int)laengeStein); }
            }
            }
        }
        //Male alle Punkte aus dem Hintergrundarray als Rechtecke.
        for (int i = 0; i < gameCycle.getFeld().length; i++){
            for (int j = 0; j < gameCycle.getFeld()[i].length; j++){
                if (gameCycle.getFeld()[i][j]){
                    g.setColor(Color.BLUE);
                    g.fillRect(yPos(j), xPos(i), (int)breiteStein, (int)laengeStein);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A'){
            gameCycle.getAktuellerSpielstein().linksBewegen();
            repaint();
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D'){
            gameCycle.getAktuellerSpielstein().rechtsBewegen();
            repaint();
        }else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S'){
            gameCycle.runde();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE){
            gameCycle.getAktuellerSpielstein().rotieren();
            repaint();
        }
    }
}
