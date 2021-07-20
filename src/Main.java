import Controller.TetrisController;
import Model.Spielfeld;
import View.AnzeigenController;
import View.TetrisMainframe;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            Spielfeld spielfeld = new Spielfeld(20, 10);
            TetrisController controller = new TetrisController(spielfeld);
            AnzeigenController anzeigenController = new AnzeigenController(spielfeld, controller);
            controller.add(anzeigenController);
            TetrisMainframe tmf = new TetrisMainframe(anzeigenController);
        }).start();
    }
}
