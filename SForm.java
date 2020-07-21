public class SForm extends Spielstein{

    SForm(int startPosx, int startPosy, GameCycle gameCycle){
        super(startPosx, startPosy, new boolean[][]{
                                           {false, false, false, false},
                                           {true, true,  false,  false},
                                           {false, true,  true,  false},
                                           {false, false, false, false}}, gameCycle);

    }
}
