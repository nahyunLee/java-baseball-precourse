package baseball.application;

import baseball.domain.GameResult;
import baseball.domain.PlayerNumbers;
import baseball.domain.policy.GameStatusPolicy;
import baseball.domain.strategy.GenerateNumberStrategy;
import baseball.domain.strategy.GenerateRandomNumberStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static baseball.domain.fixtures.customPlayerNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BaseBallServiceTest {

    private BaseBallService baseBallService = new BaseBallService(new GenerateRandomNumberStrategy());

    private static Stream<Arguments> playGameMethodSource() {
        return Stream.of(
                Arguments.of(customPlayerNumbers(1, 2, 3), GameStatusPolicy.END, 3, 0),
                Arguments.of(customPlayerNumbers(1, 2, 4), GameStatusPolicy.PLAYING, 2, 0),
                Arguments.of(customPlayerNumbers(2, 1, 4), GameStatusPolicy.PLAYING, 0, 2),
                Arguments.of(customPlayerNumbers(2, 3, 1), GameStatusPolicy.PLAYING, 0, 3),
                Arguments.of(customPlayerNumbers(1, 3, 2), GameStatusPolicy.PLAYING, 1, 2),
                Arguments.of(customPlayerNumbers(1, 4, 2), GameStatusPolicy.PLAYING, 1, 1),
                Arguments.of(customPlayerNumbers(4, 5, 6), GameStatusPolicy.PLAYING, 0, 0),
                Arguments.of(customPlayerNumbers(1, 5, 4), GameStatusPolicy.PLAYING, 1, 0),
                Arguments.of(customPlayerNumbers(4, 1, 9), GameStatusPolicy.PLAYING, 0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("playGameMethodSource")
    @DisplayName("야구 게임을 한라운드 진행한다.")
    void testPlayGame(PlayerNumbers computerGameNumber, GameStatusPolicy gameStatus, int strike, int ball) {
        //given
        String playerInputString = "123";

        //when
        GameResult gameResult = baseBallService.playGameOneRound(computerGameNumber, playerInputString);

        //then
        assertAll(
                () -> assertThat(gameResult.getGameStatus()).isEqualTo(gameStatus),
                () -> assertThat(gameResult.getStrikeNum()).isEqualTo(strike),
                () -> assertThat(gameResult.getBallNum()).isEqualTo(ball)
        );
    }

    @Test
    @DisplayName("컴퓨터 플레이어의 랜덤 숫자를 생성한다.")
    void testGenerateComputerNumbers() {
        //given
        GenerateNumberStrategy generateRandomNumberStrategy = new GenerateRandomNumberStrategy();

        //when
        PlayerNumbers computer = baseBallService.generateComputerNumbers(generateRandomNumberStrategy);

        //then
        assertThat(computer.getPlayerNumbers()).hasSize(3);
    }

    @Test
    @DisplayName("사용자의 입력값이 빈칸이 포함되면 IllegalArgumentException을 반환한다.")
    void testPlayGame_withBlankInput() {
        //given
        String playerInputString = "1 23";
        PlayerNumbers computerGameNumber = PlayerNumbers.createPlayerNumbers(Arrays.asList(1, 2, 4));

        //when then
        assertThatThrownBy(() -> baseBallService.playGameOneRound(computerGameNumber, playerInputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백 없이 입력해주세요.");
    }

    @Test
    @DisplayName("사용자의 입력 숫자가 3개가 아니라면 IllegalArgumentException을 반환한다.")
    void testPlayGame_withInvalidSize() {
        //given
        String playerInputString = "23";
        PlayerNumbers computerGameNumber = PlayerNumbers.createPlayerNumbers(Arrays.asList(1, 2, 4));

        //when then
        assertThatThrownBy(() -> baseBallService.playGameOneRound(computerGameNumber, playerInputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자는 3이상을 입력해야합니다.");
    }

    @Test
    @DisplayName("사용자의 입력값에 숫자가 아닌 문자가 포함되었다면 IllegalArgumentException을 반환한다.")
    void testPlayGame_withNotNumber() {
        //given
        String playerInputString = "2##3";
        PlayerNumbers computerGameNumber = PlayerNumbers.createPlayerNumbers(Arrays.asList(1, 2, 4));

        //when then
        assertThatThrownBy(() -> baseBallService.playGameOneRound(computerGameNumber, playerInputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력할 수 있습니다.");
    }

    @Test
    @DisplayName("사용자의 입력 숫자가 1~9사이의 수가 아니라면IllegalArgumentException을 반환한다.")
    void testPlayGame_withInvalidNumberRange() {
        //given
        String playerInputString = "011";
        PlayerNumbers computerGameNumber = PlayerNumbers.createPlayerNumbers(Arrays.asList(1, 2, 4));

        //when then
        assertThatThrownBy(() -> baseBallService.playGameOneRound(computerGameNumber, playerInputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자는 1부터 9까지 입력해야합니다.");
    }
}
