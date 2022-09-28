package baseball.domain;

import java.util.Collections;
import java.util.List;

public class PlayerNumbers {

    private List<GameNumber> playerGameNumbers;

    private PlayerNumbers(List<GameNumber> playerGameNumbers) {
        this.playerGameNumbers = playerGameNumbers;
    }

    public static PlayerNumbers createPlayerNumbers(List<GameNumber> playerGameNumbers) {
        validNumberListSize(playerGameNumbers.size());
        validNumberDifference(playerGameNumbers);
        return new PlayerNumbers(playerGameNumbers);
    }

    private static void validNumberListSize(int numberListSize) {
        if (numberListSize != GameRuleConstants.GAME_NUMBER_DIGIT) {
            throw new ArrayIndexOutOfBoundsException("게임 숫자는 세개의 수로 이루어져야 합니다.");
        }
    }

    private static void validNumberDifference(List<GameNumber> playerGameNumbers) {
        if (isContainSameNumber(playerGameNumbers)) {
            throw new NumberFormatException("게임 숫자는 모두 다른 수로 구성되어야 합니다.");
        }
    }

    private static boolean isContainSameNumber(List<GameNumber> playerGameNumbers) {
        GameNumber firstNumber = playerGameNumbers.get(0);
        GameNumber secondNumber = playerGameNumbers.get(1);
        GameNumber thirdNumber = playerGameNumbers.get(2);

        return firstNumber.isSame(secondNumber) || secondNumber.isSame(thirdNumber) || thirdNumber.isSame(firstNumber);
    }

    public List<GameNumber> getPlayerNumbers() {
        return Collections.unmodifiableList(playerGameNumbers);
    }

    public boolean isSamePositionNumber(GameNumber number, int position) {
        return playerGameNumbers.get(position).isSame(number);
    }

    public boolean isAnotherPositionNumber(GameNumber number, int position) {
        if (!this.isContainsGameNumber(number)) {
            return false;
        }

        return this.getIndexOf(number) != position;
    }

    private int getIndexOf(GameNumber gameNumber) {
        int index;

        for (index = 0; index < playerGameNumbers.size(); index++) {
            if (gameNumber.isSame(playerGameNumbers.get(index))) {
                break;
            }
        }

        return index;
    }

    private boolean isContainsGameNumber(GameNumber gameNumber) {
        for (GameNumber playerGameNumber : playerGameNumbers) {
            if (gameNumber.isSame(playerGameNumber)) {
                return true;
            }
        }
        return false;
    }

    public GameNumber getNumber(int position) {
        return playerGameNumbers.get(position);
    }
}
