public class LForm extends Spielstein{

    LForm(int startPosx, int startPosy, GameCycle gameCycle){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {false, true,  false, false},
                                           {false, true,  false, false},
                                           {false, true,  true,  false}}, gameCycle);

    }

}
