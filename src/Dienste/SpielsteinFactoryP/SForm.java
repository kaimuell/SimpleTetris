package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class SForm extends Spielstein {

    SForm(int startPosx, int startPosy){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {true, true,  false,  false},
                                           {false, true,  true,  false},
                                           {false, false, false, false}});

    }
}
