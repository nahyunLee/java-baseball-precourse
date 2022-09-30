package baseball.domain;

import baseball.domain.strategy.GenerateNumberStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static baseball.domain.GameRuleConstants.GAME_NUMBER_DIGIT;

public class PlayerNumbers {

    private List<GameNumber> playerGameNumbers;

    private PlayerNumbers(List<GameNumber> playerGameNumbers) {
        this.playerGameNumbers = playerGameNumbers;
    }

    public static PlayerNumbers createPlayerNumbers(List<Integer> playerGameNumbers) {
        validNumberListSize(playerGameNumbers.size());
        validNumberDifference(playerGameNumbers);

        return new PlayerNumbers(intToGameNumber(playerGameNumbers));
    }

    public static PlayerNumbers createAutoPlayerNumbers(GenerateNumberStrategy generateNumberStrategy) {
        List<Integer> gameNumbers = new ArrayList<>();
        for (int i = 0; i < GAME_NUMBER_DIGIT; i++) {
            gameNumbers.add(generateNumberStrategy.generateNumber());
        }

        return createPlayerNumbers(gameNumbers);
    }

    private static void validNumberListSize(int numberListSize) {
        if (numberListSize != GAME_NUMBER_DIGIT) {
            throw new ArrayIndexOutOfBoundsException("게임 숫자는 세개의 수로 이루어져야 합니다.");
        }
    }

    private static void validNumberDifference(List<Integer> playerGameNumbers) {
        if (isContainSameNumber(playerGameNumbers)) {
            throw new NumberFormatException("게임 숫자는 모두 다른 수로 구성되어야 합니다.");
        }
    }

    private static boolean isContainSameNumber(List<Integer> playerGameNumbers) {
        return playerGameNumbers.size() != new HashSet<>(playerGameNumbers).size();
    }

    private static List<GameNumber> intToGameNumber(List<Integer> playerNumbers) {
        List<GameNumber> gameNumbers = new ArrayList<>();

        for (Integer number : playerNumbers) {
            gameNumbers.add(GameNumber.createNumber(number));
        }

        return gameNumbers;
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
