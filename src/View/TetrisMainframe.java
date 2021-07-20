package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisMainframe extends JFrame  {


    public TetrisMainframe(AnzeigenController anzeigenController){
        super("SimpleTetris");
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        leftPanel.add(anzeigenController.getGameCanvas());
        Label naechsterSteinLabel = new Label("NAECHSTER STEIN :");
        naechsterSteinLabel.setSize(200, 30);
        rightPanel.add(anzeigenController.getPunkteLabel());
        JPanel steinPanel = new JPanel();
        steinPanel.add(naechsterSteinLabel);
        steinPanel.add(anzeigenController.getVorschauCanvas());
        rightPanel.add(steinPanel);
        JPanel steuerPanel = new JPanel(new GridLayout(4,1));
        Label steuerungslabel1 = new Label("A = links, D = rechts");
        steuerungslabel1.setPreferredSize(new Dimension(200,30));
        steuerPanel.add(steuerungslabel1);
        Label steuerungslabel2 = new Label("S = runter");
        steuerungslabel2.setPreferredSize(new Dimension(200,30));
        steuerPanel.add(steuerungslabel2);
        Label steuerungslabel3 = new Label("SPACE = rotieren.");
        steuerungslabel3.setPreferredSize(new Dimension(200,30));
        steuerPanel.add(steuerungslabel3);
        rightPanel.add(steuerPanel);
        rightPanel.add(anzeigenController.getStartButton());
        this.add(leftPanel);
        this.add(rightPanel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.pack();
    }


}
