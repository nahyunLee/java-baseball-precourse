package baseball.domain;

import java.util.Collections;
import java.util.HashSet;
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
            throw new NumberFormatException("게임 숫자는 세개의 수로 이루어져야 합니다.");
        }
    }

    private static void validNumberDifference(List<GameNumber> playerGameNumbers) {
        if (playerGameNumbers.size() != new HashSet<>(playerGameNumbers).size()) {
            throw new NumberFormatException("게임 숫자는 모두 다른 수로 구성되어야 합니다.");
        }
    }

    public List<GameNumber> getPlayerNumbers() {
        return Collections.unmodifiableList(playerGameNumbers);
    }
}
