package Entities;

public class Spielstein {
    private int posX, posY;
    private boolean[][] form;


    public Spielstein(int posX, int posY, boolean[][] form){
        this.posX = posX;
        this.posY = posY;
        this.form = form;
    }

    public boolean[][] getForm() {
        return form;
    }

    public void setForm(boolean[][] form) {
        this.form = form;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
