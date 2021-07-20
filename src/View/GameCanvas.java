package View;

import Controller.SpielsteinController;
import Controller.Steuerung;
import Model.ModelViewInterface;
import Model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


//Canvas zur Anzeige des laufenden Spiels.
class GameCanvas extends JPanel implements KeyListener {
    private Steuerung steuerung;
    private double breiteStein;
    private double laengeStein;
    private ModelViewInterface model;
    private BufferedImage backgroundImage;
    private static int height = 400;
    private static int width = 200;



    GameCanvas(ModelViewInterface model, Steuerung steuerung){
        this.model = model;
        this.steuerung = steuerung;
        this.setPreferredSize(new Dimension(width, height));
        breiteStein =  (width / (model.getFeld()[1].length));
        laengeStein =  (height /(model.getFeld().length));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
     }

     public void createBackgroundImage(){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
         for (int i = 0; i < model.getFeld().length; i++){
             for (int j = 0; j < model.getFeld()[i].length; j++){
                 if (model.getFeld()[i][j]){
                     g.setColor(Color.BLUE);
                     g.fillRect(yPos(j), xPos(i), (int)breiteStein, (int)laengeStein);
                 }
             }
         }
         this.backgroundImage = img;
     }


    private int yPos(int x){
        return (int) ((x * breiteStein));
    }

    private int xPos (int y){
        return (int) (this.getHeight() - (y* laengeStein));
    }

    @Override
    public void paintComponent(Graphics g) {
        //Male alle Punkte aus aktuellem Entities.Spielstein als Rechtecke.
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        g.setColor(Color.RED);
        for (int spX = 0; spX < model.getSpielstein().getForm().length; spX++) {
            for (int spY = 0; spY < model.getSpielstein().getForm()[0].length; spY++) {
                if ((spX + model.getSpielstein().getPosX()) < model.getFeld().length) {
                    if (model.getSpielstein().getForm()[spX][spY]) {
                        g.drawRect(yPos(model.getSpielstein().getPosY() + spY), xPos(model.getSpielstein().getPosX() + spX),
                                (int) breiteStein, (int) laengeStein);
                    }
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A'){
            steuerung.linksBewegen();
            repaint();
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D'){
            steuerung.rechtsBewegen();
            repaint();
        }else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S'){
            steuerung.naechteRunde();
            repaint();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE){
            steuerung.rotieren();
            repaint();
        }
    }
}
