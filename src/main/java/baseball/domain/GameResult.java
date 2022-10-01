package baseball.domain;

import baseball.domain.policy.GameStatusPolicy;

import static baseball.domain.GameRuleConstants.GAME_NUMBER_DIGIT;
import static baseball.domain.policy.GameStatusPolicy.END;
import static baseball.domain.policy.GameStatusPolicy.PLAYING;

public class GameResult {

    private GameStatusPolicy gameStatus;
    private int strikeNum;
    private int ballNum;
    private boolean isNothing;

    public static GameResult createGameResult(int strikeNum, int ballNum) {
        return new Builder()
                .gameStatus(getGameStatus(strikeNum))
                .strikeNum(strikeNum)
                .ballNum(ballNum)
                .isNothing(isNothingCorrect(strikeNum, ballNum))
                .build();
    }

    public static GameResult createPlayingGameResult() {
        return new Builder()
                .gameStatus(PLAYING)
                .build();
    }

    private static GameStatusPolicy getGameStatus(int strikeNum) {
        if (strikeNum == GAME_NUMBER_DIGIT) {
            return END;
        }
        return PLAYING;
    }

    private static boolean isNothingCorrect(int strikeNum, int ballNum) {
        return strikeNum == 0 && ballNum == 0;
    }

    public GameStatusPolicy getGameStatus() {
        return gameStatus;
    }

    public int getStrikeNum() {
        return strikeNum;
    }

    public int getBallNum() {
        return ballNum;
    }

    public boolean isNothing() {
        return isNothing;
    }

    public static final class Builder {
        private GameStatusPolicy gameStatus;
        private int strikeNum;
        private int ballNum;
        private boolean isNothing;

        public Builder() {
        }

        public Builder gameStatus(GameStatusPolicy val) {
            gameStatus = val;
            return this;
        }

        public Builder strikeNum(int val) {
            strikeNum = val;
            return this;
        }

        public Builder ballNum(int val) {
            ballNum = val;
            return this;
        }

        public Builder isNothing(boolean val) {
            isNothing = val;
            return this;
        }

        public GameResult build() {
            GameResult gameResult = new GameResult();
            gameResult.gameStatus = gameStatus;
            gameResult.strikeNum = strikeNum;
            gameResult.ballNum = ballNum;
            gameResult.isNothing = isNothing;

            return gameResult;
        }
    }
}
