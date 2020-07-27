import java.awt.*;


//Canvas zur Anzeige des nächsten Spielsteins

public class VorschauCanvas extends Canvas {
    private Spielstein spielstein;
    private int breiteFeld, hoeheFeld;

    VorschauCanvas(Spielstein spielstein) {
        setBackground(Color.lightGray);
        setSize(100, 100);
        this.spielstein = spielstein;
        this.hoeheFeld = (this.getWidth() / (spielstein.getForm().length + 2));
        this.breiteFeld = (this.getWidth() / (spielstein.getForm()[0].length + 2));
    }

    public void setSpielstein(Spielstein spielstein) {
        this.spielstein = spielstein;
    }

    private int yPos(int x) {
        return (int)  (this.getHeight() - (x +2) * breiteFeld);
    }
    private int xPos(int y) { return (int)  (y + 1) * hoeheFeld; }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
        for (int x = 0; x < spielstein.getForm().length; x++) {
            for (int y = 0; y < spielstein.getForm()[x].length; y++) {
                if (spielstein.getForm()[x][y]) {
                    g.drawRect(yPos(y), xPos(x), breiteFeld, hoeheFeld);
                }
            }
        }
    }
}