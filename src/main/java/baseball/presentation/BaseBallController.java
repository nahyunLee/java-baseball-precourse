package baseball.presentation;

import baseball.application.BaseBallService;
import baseball.domain.GameResult;
import baseball.domain.PlayerNumbers;
import baseball.domain.policy.GameStatusPolicy;
import baseball.domain.strategy.GenerateNumberStrategy;
import baseball.domain.strategy.GenerateRandomNumberStrategy;

import static baseball.presentation.View.getPlayerRestartAnswer;
import static baseball.presentation.View.printGameResult;


public class BaseBallController {

    private BaseBallService baseballService;
    private GenerateNumberStrategy generateNumberStrategy;

    public BaseBallController() {
        this.baseballService = new BaseBallService();
        this.generateNumberStrategy = new GenerateRandomNumberStrategy();
    }

    public void startGame() {
        int resumeAnswer;

        do {
            playGame();
            resumeAnswer = getPlayerRestartAnswer();
        } while (resumeAnswer == 1);
    }

    private void playGame() {
        PlayerNumbers computerNumbers = PlayerNumbers.createAutoPlayerNumbers(generateNumberStrategy);
        GameStatusPolicy gameStatus;

        do {
            GameResult gameResult = playOneRound(computerNumbers);
            gameStatus = gameResult.getGameStatus();
        } while (gameStatus != GameStatusPolicy.END);
    }

    private GameResult playOneRound(PlayerNumbers computerNumbers) {
        String playerInputString = View.getPlayersNumber();

        GameResult gameResult = baseballService.playGameOneRound(computerNumbers, playerInputString);
        printGameResult(gameResult);

        return gameResult;
    }
}
