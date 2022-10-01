package baseball.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

import static baseball.domain.GameRuleConstants.*;

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
