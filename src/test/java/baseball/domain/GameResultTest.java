package baseball.domain;

import baseball.domain.policy.GameStatusPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameResultTest {

    @Test
    @DisplayName("게임 결과를 생성한다.")
    void testCreateResult() {
        //given
        int strike = 0;
        int ball = 1;

        //when then
        assertDoesNotThrow(() -> GameResult.createGameResult(strike, ball));
    }

    @Test
    @DisplayName("스트라이크가 3개인 게임 결과의 상태는 '게임 종료'이다")
    void testGameStatus_withThreeStrike() {
        //given
        int strike = 3;
        int ball = 0;

        //when
        GameResult gameResult = GameResult.createGameResult(strike, ball);

        //then
        assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.END);
    }

    @Test
    @DisplayName("스트라이크가 3개가 아닌 게임 결과의 상태는 '게임 중'이다")
    void testGameStatus_withNotThreeStrike() {
        //given
        int strike = 2;
        int ball = 1;

        //when
        GameResult gameResult = GameResult.createGameResult(strike, ball);

        //then
        assertThat(gameResult.getGameStatus()).isEqualTo(GameStatusPolicy.PLAYING);
    }

    @Test
    @DisplayName("스트라이크나 볼이 전혀 없는 게임의 낫싱 상태는 true이다")
    void testGameStatus_withNothing() {
        //given
        int strike = 0;
        int ball = 0;

        //when
        GameResult gameResult = GameResult.createGameResult(strike, ball);

        //then
        assertThat(gameResult.isNothing()).isTrue();
    }

    @Test
    @DisplayName("스트라이크나 볼이 있는 게임의 낫싱 상태는 false이다")
    void testGameStatus_withSameNumber() {
        //given
        int strike = 1;
        int ball = 1;

        //when
        GameResult gameResult = GameResult.createGameResult(strike, ball);

        //then
        assertThat(gameResult.isNothing()).isFalse();
    }
}
