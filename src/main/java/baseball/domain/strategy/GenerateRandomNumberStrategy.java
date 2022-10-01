package baseball.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static baseball.domain.GameRuleConstants.MAX_NUMBER;
import static baseball.domain.GameRuleConstants.MIN_NUMBER;

public class GenerateRandomNumberStrategy implements GenerateNumberStrategy {

    @Override
    public List<Integer> generateNumbers() {
        List<Integer> generatedNumbers;

        do {
            generatedNumbers = new ArrayList<>();
            generatedNumbers.add(Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER));
            generatedNumbers.add(Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER));
            generatedNumbers.add(Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER));
        } while (this.isUniqueNumbers(generatedNumbers));

        return generatedNumbers;
    }

    private boolean isUniqueNumbers(List<Integer> numberList) {
        return numberList.size() != new HashSet<>(numberList).size();
    }
}
