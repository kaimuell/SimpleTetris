public class Spielstein {
    private int posX, posY;
    private boolean[][] form;
    private GameCycle gc;

    Spielstein(int posX, int posY, boolean[][] form, GameCycle gameCycle){
        this.posX = posX;
        this.posY = posY;
        this.form = form;
        this.gc = gameCycle;
    }

    public void linksBewegen(){
        if (!gc.kollisionsabfrage(new Spielstein(posX, posY-1, form, gc))){
            posY -=1;
        }
    }
    public void rechtsBewegen(){
        if (!gc.kollisionsabfrage(new Spielstein(posX, posY+1, form, gc))){
            posY +=1;
        }
    }
    public void PosXMinusEins(){
        this.posX -= 1;
    }
    public boolean[][] getForm() {
        return form;
    }
    public int getPosX(){
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void rotieren(){
        boolean[][] neueForm = new boolean[4][4];
        for (int i = 0; i < form.length; i++){
            for (int j = 0; j < form.length; j++){
                neueForm[j][i] = form[i][(form.length -1 )-j];
            }
        }
        if (!gc.kollisionsabfrage(new Spielstein(posX, posY, neueForm, gc))) {
            form = neueForm;
        }
    }
}
