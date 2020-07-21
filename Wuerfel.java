public class Wuerfel extends Spielstein {

    Wuerfel(int startPosx, int startPosy, GameCycle gameCycle){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {false, true,  true,  false},
                                           {false, true,  true,  false},
                                           {false, false, false, false}}, gameCycle);

    }

}
