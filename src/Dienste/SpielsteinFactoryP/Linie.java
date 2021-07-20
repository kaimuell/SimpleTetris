package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class Linie extends Spielstein {


    Linie(int startPosx, int startPosy){
        super(startPosx, startPosy, new boolean[][]{
                                          {false, true, false, false},
                                          {false, true, false,  false},
                                          {false, true, false,  false},
                                          {false, true, false, false}});

    }

}
