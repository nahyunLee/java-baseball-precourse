package baseball.domain;

import baseball.domain.policy.GameStatusPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static baseball.domain.fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BaseballGameRoundTest {

    @Test
    @DisplayName("야구 게임 라운드를 생성한다.")
    void testCreateBaseballGameRound() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(one(), five(), six());

        PlayerNumbers computerNumber = PlayerNumbers.createPlayerNumbers(computerNumberList);
        PlayerNumbers playerNumbers = PlayerNumbers.createPlayerNumbers(playerNumbersList);

        //when then
        assertDoesNotThrow(() -> BaseballGameRound.createBaseballGame(computerNumber,playerNumbers));
    }

    @Test
    @DisplayName("컴퓨터의 세자리 수를 모두 맞춘다면 게임이 끝난다.")
    void testPlayGame() {
        //given
        List<GameNumber> sameNumberList = Arrays.asList(one(), two(), three());

        BaseballGameRound baseballGameRound = oneRoundGame(sameNumberList, sameNumberList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.END),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(3),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(0),
                () -> assertThat(gameResult.isNothing()).isFalse()
        );
    }

    @Test
    @DisplayName("컴퓨터의 세자리 수를 모두 맞추지 못한다면 게임 진행 상태를 반환한다.")
    void testPlayGame_withNotEnd() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(four(), four(), six());

        BaseballGameRound baseballGameRound = oneRoundGame(computerNumberList, playerNumbersList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING)
        );
    }

    @Test
    @DisplayName("같은 수가 같은 자리에 있다면 스트라이크 힌트와 그 개수를 반환한다.")
    void testPlayGame_withStrikeNumbers() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(one(), two(), six());

        BaseballGameRound baseballGameRound = oneRoundGame(computerNumberList, playerNumbersList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(2),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(0),
                () -> assertThat(gameResult.isNothing()).isFalse()
        );
    }

    @Test
    @DisplayName("같은 수가 다른 자리에 있다면 볼 힌트와 그 개수를 반환한다.")
    void testPlayGame_withBallNumbers() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(two(), one(), six());

        BaseballGameRound baseballGameRound = oneRoundGame(computerNumberList, playerNumbersList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(0),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(2),
                () -> assertThat(gameResult.isNothing()).isFalse()
        );
    }

    @Test
    @DisplayName("같은 수가 전혀 없다면 낫싱 힌트를 반환한다.")
    void testPlayGame_withNothing() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(four(), five(), six());

        BaseballGameRound baseballGameRound = oneRoundGame(computerNumberList, playerNumbersList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(0),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(0),
                () -> assertThat(gameResult.isNothing()).isTrue()
        );
    }

    @Test
    @DisplayName("볼과 스트라이크 힌트는 함꼐 반환될 수 있다.")
    void testPlayGame_withBallAndStrike() {
        //given
        List<GameNumber> computerNumberList = Arrays.asList(one(), two(), three());
        List<GameNumber> playerNumbersList = Arrays.asList(one(), three(), two());

        BaseballGameRound baseballGameRound = oneRoundGame(computerNumberList, playerNumbersList);

        //when
        GameResult gameResult = baseballGameRound.playGame();

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(1),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(2),
                () -> assertThat(gameResult.isNothing()).isFalse()
        );
    }
}
