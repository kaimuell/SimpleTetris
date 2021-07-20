package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class LFormGespiegelt extends Spielstein {
    LFormGespiegelt(int startPosx, int startPosy) {
        super(startPosx, startPosy, new boolean[][]{
                {false, false, false, false},
                {false, false, true, false},
                {false, false, true, false},
                {false, true, true, false}});
    }
}
