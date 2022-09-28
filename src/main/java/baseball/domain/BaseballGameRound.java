package baseball.domain;

public class BaseballGameRound {

    private PlayerNumbers computerNumbers;
    private PlayerNumbers playerNumbers;
    private int strikeNum;
    private int ballNum;

    public BaseballGameRound(PlayerNumbers computerNumbers, PlayerNumbers playerNumbers) {
        this.computerNumbers = computerNumbers;
        this.playerNumbers = playerNumbers;
    }

    public static BaseballGameRound createBaseballGame(PlayerNumbers computerNumbers, PlayerNumbers playerNumbers) {
        BaseballGameRound baseballGameRound = new BaseballGameRound(computerNumbers, playerNumbers);
        baseballGameRound.strikeNum = 0;
        baseballGameRound.ballNum = 0;

        return baseballGameRound;
    }

    public GameResult playGame() {
        for (int i = 0; i < 3; i++) {
            checkStrike(i);
            checkBall(i);
        }

        return getGameResult();
    }

    private void checkStrike(int position) {
        if (computerNumbers.isSamePositionNumber(playerNumbers.getNumber(position), position)) {
            strikeNum++;
        }
    }

    private void checkBall(int position) {
        if (computerNumbers.isAnotherPositionNumber(playerNumbers.getNumber(position), position)) {
            ballNum++;
        }
    }

    private GameResult getGameResult() {
        return GameResult.createGameResult(strikeNum, ballNum);
    }
}
