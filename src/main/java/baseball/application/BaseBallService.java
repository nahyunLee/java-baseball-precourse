package baseball.application;

import baseball.domain.BaseballGameRound;
import baseball.domain.GameResult;
import baseball.domain.PlayerNumbers;
import baseball.domain.strategy.GenerateNumberStrategy;

import java.util.ArrayList;
import java.util.List;

import static baseball.domain.GameRuleConstants.*;
import static java.lang.String.format;

public class BaseBallService {

    public PlayerNumbers generateComputerNumbers(GenerateNumberStrategy generateNumberStrategy) {
        return PlayerNumbers.createAutoPlayerNumbers(generateNumberStrategy);
    }

    public GameResult playGameOneRound(PlayerNumbers computerNumbers, String inputNumberString) {
        String[] inputNumberStrings = inputNumberString.split("");

        validInputNumbers(inputNumberStrings);
        List<Integer> inputNumbers = this.stringArrayToIntegerList(inputNumberStrings);

        PlayerNumbers playerNumbers = PlayerNumbers.createPlayerNumbers(inputNumbers);
        BaseballGameRound gameRound = BaseballGameRound.createBaseballGame(computerNumbers, playerNumbers);

        return gameRound.playGame();
    }

    private void validInputNumbers(String[] inputNumbers) {
        this.validNumbersBlank(inputNumbers);
        this.validNumbersFormat(inputNumbers);
        this.validNumberCount(inputNumbers);
        this.validNumbersRange(inputNumbers);
    }

    private void validNumbersBlank(String[] inputNumbers) {
        for (String inputNumberString : inputNumbers) {
            this.validNumberBlank(inputNumberString);
        }
    }

    private void validNumberBlank(String numberString) {
        if (numberString.equals(" ")) {
            throw new IllegalArgumentException("공백 없이 입력해주세요.");
        }
    }

    private void validNumberFormat(String numberString) {
        try {
            Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private void validNumberCount(String[] inputNumbers) {
        if (inputNumbers.length != GAME_NUMBER_DIGIT) {
            throw new IllegalArgumentException(format("숫자는 %d이상을 입력해야합니다.", GAME_NUMBER_DIGIT));
        }
    }

    private void validNumbersRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(format("숫자는 %d부터 %d까지 입력해야합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    private void validNumbersFormat(String[] inputNumbers) {
        for (String inputNumberString : inputNumbers) {
            this.validNumberFormat(inputNumberString);
        }
    }

    private void validNumbersRange(String[] inputNumbers) {
        for (String inputNumberString : inputNumbers) {
            validNumbersRange(Integer.parseInt(inputNumberString));
        }
    }

    private List<Integer> stringArrayToIntegerList(String[] inputNumberStrings) {
        List<Integer> inputNumber = new ArrayList<>();
        for (String stringNumber : inputNumberStrings) {
            inputNumber.add(Integer.parseInt(stringNumber));
        }

        return inputNumber;
    }
}
