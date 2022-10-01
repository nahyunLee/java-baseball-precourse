package baseball.domain;

import baseball.domain.strategy.GenerateNumberStrategy;

import java.util.*;

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
        List<Integer> randomNumbers = generateNumberStrategy.generateNumbers();

        return new PlayerNumbers(intToGameNumber(randomNumbers));
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
        Map<Boolean, Integer> indexGameNumberMap = new HashMap<>();
        int index;

        for (index = 0; index < playerGameNumbers.size(); index++) {
            indexGameNumberMap.put(this.isSameNumberIndex(index, gameNumber), index);
        }

        return indexGameNumberMap.get(true);
    }

    private boolean isSameNumberIndex(int index, GameNumber gameNumber) {
        return gameNumber.isSame(playerGameNumbers.get(index));
    }

    private boolean isContainsGameNumber(GameNumber gameNumber) {
        List<Boolean> containsList = new ArrayList<>();

        for (GameNumber playerGameNumber : playerGameNumbers) {
            containsList.add(this.isSameNumber(gameNumber, playerGameNumber));
        }
        return containsList.contains(true);
    }

    private boolean isSameNumber(GameNumber gameNumber, GameNumber playerGameNumber) {
        return gameNumber.isSame(playerGameNumber);
    }

    public GameNumber getNumber(int position) {
        return playerGameNumbers.get(position);
    }
}
