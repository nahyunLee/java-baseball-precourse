package baseball.domain;

import static baseball.domain.GameRuleConstants.MAX_NUMBER;
import static baseball.domain.GameRuleConstants.MIN_NUMBER;
import static java.lang.String.format;

public class GameNumber {

    private int number;

    private GameNumber(int number) {
        this.number = number;
    }

    public static GameNumber createNumber(int number) {
        validNumberRange(number);
        return new GameNumber(number);
    }

    private static void validNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new NumberFormatException(format("숫자는 %d 이상 %d 이하여야 합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    public int getNumber() {
        return number;
    }
}
