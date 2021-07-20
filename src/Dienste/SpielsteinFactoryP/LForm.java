package Dienste.SpielsteinFactoryP;

import Entities.Spielstein;

class LForm extends Spielstein {

    LForm(int startPosx, int startPosy){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {false, true,  false, false},
                                           {false, true,  false, false},
                                           {false, true,  true,  false}});

    }

}
