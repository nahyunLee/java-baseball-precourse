package baseball;

import baseball.presentation.BaseBallController;

public class Application {
    public static void main(String[] args) {
        BaseBallController controller = new BaseBallController();
        controller.startGame();
    }
}
