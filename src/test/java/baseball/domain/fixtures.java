package baseball.domain;

import java.util.Arrays;
import java.util.List;

public class fixtures {

    public static GameNumber one() {
        return GameNumber.createNumber(1);
    }

    public static GameNumber two() {
        return GameNumber.createNumber(2);
    }

    public static GameNumber three() {
        return GameNumber.createNumber(3);
    }

    public static GameNumber four() {
        return GameNumber.createNumber(4);
    }

    public static GameNumber five() {
        return GameNumber.createNumber(5);
    }

    public static GameNumber six() {
        return GameNumber.createNumber(6);
    }

    public static GameNumber seven() {
        return GameNumber.createNumber(7);
    }

    public static GameNumber eight() {
        return GameNumber.createNumber(8);
    }

    public static GameNumber nine() {
        return GameNumber.createNumber(9);
    }

    public static BaseballGameRound oneRoundGame(List<GameNumber> computerNumberList, List<GameNumber> playerNumberList) {
        PlayerNumbers computerNumbers = PlayerNumbers.createPlayerNumbers(computerNumberList);
        PlayerNumbers playerNumbers = PlayerNumbers.createPlayerNumbers(playerNumberList);

        return BaseballGameRound.createBaseballGame(computerNumbers, playerNumbers);
    }

    public static PlayerNumbers oneTwoThree() {
        List<GameNumber> gameNumbers = Arrays.asList(one(), two(), three());

        return PlayerNumbers.createPlayerNumbers(gameNumbers);
    }
}
