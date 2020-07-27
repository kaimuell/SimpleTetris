import java.awt.*;
import java.awt.event.*;

public class TetrisMainframe extends Frame  {

    final int XOFFSET = 300;

    TetrisMainframe(){
        super("SimpleTetris");
        setVisible(true);
        setSize(500, 500);
        AnzeigenController anzeigenController = new AnzeigenController();
        this.add(anzeigenController.getGameCanvas());
        this.add(anzeigenController.getVorschauCanvas());
        Label naechsterSteinLabel = new Label("NAECHSTER STEIN :");
        naechsterSteinLabel.setSize(200, 30);
        naechsterSteinLabel.setLocation(XOFFSET, 70);
        this.add(anzeigenController.punkteLabel);
        this.add(naechsterSteinLabel);
        Label steuerungslabel1 = new Label("A = links, D = rechts");
        steuerungslabel1.setSize(200,30);
        steuerungslabel1.setLocation(XOFFSET, 250);
        this.add(steuerungslabel1);
        Label steuerungslabel2 = new Label("S = runter");
        steuerungslabel2.setSize(200,30);
        steuerungslabel2.setLocation(XOFFSET, 280);
        this.add(steuerungslabel2);
        Label steuerungslabel3 = new Label("SPACE = rotieren.");
        steuerungslabel3.setSize(200,30);
        steuerungslabel3.setLocation(XOFFSET, 310);
        this.add(steuerungslabel3);
        this.add(anzeigenController.startButton);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
    }

    public static void main(String[] args){
        TetrisMainframe tmf = new TetrisMainframe();
    }
}
