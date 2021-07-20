package Controller;

import Dienste.SpielsteinFactoryP.*;
import Entities.*;
import Model.Spielfeld;
import java.util.ArrayList;


public class SpielsteinController {

    private Spielfeld spielfeld;
    private SpielsteinFactory spielsteinFactory;

    public SpielsteinController(Spielfeld spielfeld) {

        this.spielfeld = spielfeld;
        this.spielsteinFactory = new SpielsteinFactory(spielfeld.getInitPosX(), spielfeld.getInitPosY());
    }

    public Spielstein erzeugeNaechstenSpielstein() {
        return spielsteinFactory.erzeugeSpielstein();
    }



    public boolean kollisionsabfrage(Spielstein sp) {

        ArrayList<Pos2D> steinPositionen = new ArrayList<>(4);
        for (int i = 0; i < sp.getForm().length; i++) {
            for (int j = 0; j < sp.getForm()[i].length; j++) {
                if (sp.getForm()[i][j]) {
                    steinPositionen.add(new Pos2D((i + sp.getPosX()), (j + sp.getPosY())));
                }
            }
        }

        for (Pos2D p2D : steinPositionen) {
            if (p2D.getyPos() < 0 || p2D.getyPos() >= spielfeld.getFeld()[0].length || p2D.getxPos() <= 0) {
                return true;
            }
        }

        for (int i = 0; i < spielfeld.getFeld().length; i++) {
            for (int j = 0; j < spielfeld.getFeld()[i].length; j++) {
                if (spielfeld.getFeld()[i][j]) {
                    for (Pos2D p2D : steinPositionen) {
                        if ((i == p2D.getxPos()) && (j == p2D.getyPos())) {
                            return true;

                        }
                    }
                }
            }
        }
        return false;
    }


    public void rotieren(){
        Spielstein spielstein = spielfeld.getSpielstein();
        boolean[][] neueForm = new boolean[4][4];
        for (int i = 0; i < spielstein.getForm().length; i++){
            for (int j = 0; j < spielstein.getForm().length; j++){
                neueForm[j][i] = spielstein.getForm()[i][(spielstein.getForm().length -1 )-j];
            }
        }
        if (!kollisionsabfrage(new Spielstein(spielstein.getPosX(), spielstein.getPosY(), neueForm))) {
            spielstein.setForm(neueForm);
        }
    }

    public void linksBewegen(){
        Spielstein spielstein = spielfeld.getSpielstein();
        if (!kollisionsabfrage(new Spielstein(spielstein.getPosX(), spielstein.getPosY()-1, spielstein.getForm()))){
            spielstein.setPosY(spielstein.getPosY()-1);
        }
    }
    public void rechtsBewegen(){
        Spielstein spielstein = spielfeld.getSpielstein();
        if (!kollisionsabfrage(new Spielstein(spielstein.getPosX(), spielstein.getPosY()+1, spielstein.getForm()))){
            spielstein.setPosY(spielstein.getPosY()+1);
        }
    }
}
