package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class SFormGespiegelt extends Spielstein {

    SFormGespiegelt(int startPosx, int startPosy){
        super(startPosx, startPosy, new boolean[][]{
                {false, false, false, false},
                {false, true,  true,  false},
                {true, true,  false,  false},
                {false, false, false, false}}
                );

    }
}
