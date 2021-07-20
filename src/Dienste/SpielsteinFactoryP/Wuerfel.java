package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class Wuerfel extends Spielstein {

    Wuerfel(int startPosx, int startPosy){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {false, true,  true,  false},
                                           {false, true,  true,  false},
                                           {false, false, false, false}});

    }

}
