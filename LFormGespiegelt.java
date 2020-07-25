public class LFormGespiegelt extends Spielstein{
    LFormGespiegelt(int startPosx, int startPosy, GameCycle gameCycle) {
        super(startPosx, startPosy, new boolean[][]{
                {false, false, false, false},
                {false, false, true, false},
                {false, false, true, false},
                {false, true, true, false}}, gameCycle);
    }
}
