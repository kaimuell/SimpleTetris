package Model;

import Entities.Spielstein;

public interface ModelViewInterface {
    boolean[][] getFeld();
    Spielstein getSpielstein();
    Spielstein getNaechsterSpielstein();

    boolean isSpielende();
    int getPunkteStand();
}
