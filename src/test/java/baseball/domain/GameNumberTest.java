package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static baseball.domain.GameRuleConstants.MAX_NUMBER;
import static baseball.domain.GameRuleConstants.MIN_NUMBER;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameNumberTest {

    @Test
    @DisplayName("게임 숫자를 생성한다.")
    void testCreateNumber() {
        //given
        int number = 1;

        //when
        GameNumber gameNumber = GameNumber.createNumber(1);

        //then
        assertThat(gameNumber.getNumber()).isEqualTo(number);
    }

    @Test
    @DisplayName("게임 숫자 범위에 맞지 않은 수로 게임 숫자를 생성하고자 하면 NumberFormatException을 반환한다.")
    void testCreateNumber_withInvalidRangeNumber() {
        //given
        int invalidNumber = -1000;
        //when then
        assertThatThrownBy(() -> GameNumber.createNumber(invalidNumber))
                .isInstanceOf(NumberFormatException.class)
                .hasMessageContaining(format("숫자는 %d 이상 %d 이하여야 합니다.", MIN_NUMBER, MAX_NUMBER));
    }

    @ParameterizedTest
    @MethodSource("gameNumberSource")
    @DisplayName("같은 게임 숫자인지 테스트")
    void testIsSameGameNumber(int realNumber, int compareNumber, boolean isSame) {
        //given
        GameNumber realGameNumber = GameNumber.createNumber(realNumber);
        GameNumber compareGameNumber = GameNumber.createNumber(compareNumber);

        //when
        boolean isSameGameNumber = realGameNumber.isSame(compareGameNumber);

        //then
        assertThat(isSameGameNumber).isEqualTo(isSame);
    }

    private static Stream<Arguments> gameNumberSource() {
        return Stream.of(
                Arguments.of(1, 1, true),
                Arguments.of(1, 2, false)
        );
    }
}
