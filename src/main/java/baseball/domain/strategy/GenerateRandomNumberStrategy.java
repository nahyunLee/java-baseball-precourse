package baseball.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

import static baseball.domain.GameRuleConstants.MAX_NUMBER;
import static baseball.domain.GameRuleConstants.MIN_NUMBER;

public class GenerateRandomNumberStrategy implements GenerateNumberStrategy {

    @Override
    public int generateNumber() {
        return Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
    }
}
