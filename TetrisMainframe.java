import java.awt.*;
import java.awt.event.*;

public class TetrisMainframe extends Frame  {

    int XOFFSET = 300;

    TetrisMainframe(){
        super("SimpleTetris");
        setVisible(true);
        setSize(500, 500);
        GameCycle gc = new GameCycle( 20, 4, 200);
        AnzeigenController anzeigenController = new AnzeigenController(gc);
        gc.addAnzeigenController(anzeigenController);
        this.add(anzeigenController.getGameCanvas());
        this.add(anzeigenController.getVorschauCanvas());
        Label naechsterSteinLabel = new Label("NAECHSTER STEIN :");
        naechsterSteinLabel.setSize(200, 30);
        naechsterSteinLabel.setLocation(XOFFSET, 70);
        this.add(anzeigenController.punkteLabel);
        this.add(naechsterSteinLabel);
        Label steuerungslabel1 = new Label("A = links, D = rechts");
        steuerungslabel1.setSize(200,60);
        steuerungslabel1.setLocation(XOFFSET, 250);
        this.add(steuerungslabel1);
        Label steuerungslabel2 = new Label("SPACE = rotieren.");
        steuerungslabel2.setSize(200,60);
        steuerungslabel2.setLocation(XOFFSET, 300);
        this.add(steuerungslabel2);
        Button startButton = new Button("START");
        startButton.setSize(100,30);
        startButton.setLocation(XOFFSET,400);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.start();
                anzeigenController.gameCanvas.requestFocus();
            }
        });

        this.add(startButton);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }








    public static void main(String[] args){
        TetrisMainframe tmf = new TetrisMainframe();
    }
}
