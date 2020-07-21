public class Linie extends Spielstein{


    Linie(int startPosx, int startPosy, GameCycle gameCycle){
        super(startPosx, startPosy, new boolean[][]{
                                          {false, true, false, false},
                                          {false, true, false,  false},
                                          {false, true, false,  false},
                                          {false, true, false, false}}, gameCycle);

    }

}
