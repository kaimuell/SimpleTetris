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
        addKeyListener(this);
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

        boolean[][] sp = gameCycle.getAktuellerSpielstein().getForm();
        for (int spX=0;spX < sp.length; spX ++){
            for (int spY=0; spY < sp[0].length; spY++){
            if ((spX + gameCycle.getAktuellerSpielstein().getPosX()) < gameCycle.getFeld().length){
                if (sp[spX][spY]){
                    g.setColor(Color.RED);
                    g.drawRect(yPos(gameCycle.getAktuellerSpielstein().getPosY()+spY), xPos(gameCycle.getAktuellerSpielstein().getPosX()+spX),
                             (int) breiteStein, (int)laengeStein); }
            }
            }
        }
        //Male alle Punkte aus dem Hintergrundarray als Rechtecke.
        for (int i = 0; i < gameCycle.getFeld().length; i++){
            for (int j = 0; j< gameCycle.getFeld()[i].length; j++){
                if (gameCycle.getFeld()[i][j]){
                    g.setColor(Color.BLUE);
                    g.drawRect(yPos(j), xPos(i), (int)breiteStein, (int)laengeStein);

                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A'){
            System.out.println("A gedrückt.");
            gameCycle.getAktuellerSpielstein().linksBewegen();
            repaint();
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D'){
            System.out.println("d gedrückt.");
            gameCycle.getAktuellerSpielstein().rechtsBewegen();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE){
            System.out.println("Leertaste betätigt.");
            gameCycle.getAktuellerSpielstein().rotieren();
            repaint();
        }
    }
}
