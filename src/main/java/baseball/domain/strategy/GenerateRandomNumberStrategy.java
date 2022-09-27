package baseball.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class GenerateRandomNumberStrategy implements GenerateNumberStrategy {

    private final int START_NUMBER = 1;
    private final int END_NUMBER = 9;

    @Override
    public int generateNumber() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }
}
