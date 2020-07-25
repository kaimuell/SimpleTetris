public class SFormGespiegelt extends Spielstein{

    SFormGespiegelt(int startPosx, int startPosy, GameCycle gameCycle){
        super(startPosx, startPosy, new boolean[][]{
                {false, false, false, false},
                {false, true,  true,  false},
                {true, true,  false,  false},
                {false, false, false, false}}, gameCycle);

    }
}
