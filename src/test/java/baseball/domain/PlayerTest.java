package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static baseball.domain.GameRuleConstants.GAME_NUMBER_DIGIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("세개의 수로 이루어진 사용자 숫자 일급컬렉션을 생성한다.")
    void testCreatePlayerNumbers() {
        //given
        GameNumber gameNumber1 = GameNumber.createNumber(1);
        GameNumber gameNumber2 = GameNumber.createNumber(2);
        GameNumber gameNumber3 = GameNumber.createNumber(3);

        List<GameNumber> gameNumbers = Arrays.asList(gameNumber1, gameNumber2, gameNumber3);

        //when
        PlayerNumbers playerNumbers = PlayerNumbers.createPlayerNumbers(gameNumbers);

        //then
        assertThat(playerNumbers.getPlayerNumbers().size()).isEqualTo(GAME_NUMBER_DIGIT);
    }

    @Test
    @DisplayName("사용자 숫자의 개수가 3개가 아니라면 NumberFormatException을 반환한다.")
    void testCreatePlayerNumbers_withInvalidSize() {
        //given
        GameNumber gameNumber1 = GameNumber.createNumber(1);
        GameNumber gameNumber2 = GameNumber.createNumber(2);

        List<GameNumber> gameNumbers = Arrays.asList(gameNumber1, gameNumber2);

        //when then
        assertThatThrownBy(() -> PlayerNumbers.createPlayerNumbers(gameNumbers))
                .isInstanceOf(NumberFormatException.class)
                .hasMessageContaining("게임 숫자는 세개의 수로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("사용자의 숫자 중 중복된 숫자가 있다면 NumberFormatException을 반환한다.")
    void testCreatePlayerNumbers_withOverLapNumber() {
        //given
        GameNumber gameNumber1 = GameNumber.createNumber(1);
        GameNumber gameNumber2 = GameNumber.createNumber(2);

        List<GameNumber> gameNumbers = Arrays.asList(gameNumber1, gameNumber2, gameNumber2);

        //when then
        assertThatThrownBy(() -> PlayerNumbers.createPlayerNumbers(gameNumbers))
                .isInstanceOf(NumberFormatException.class)
                .hasMessageContaining("게임 숫자는 모두 다른 수로 구성되어야 합니다.");
    }
}
