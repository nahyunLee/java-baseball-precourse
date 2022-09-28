package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static baseball.domain.fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerNumbersTest {

    @Test
    @DisplayName("플레이어의 게임 숫자 일급컬렉션을 생성한다.")
    void testCreatePlayerNumbers() {
        //given
        List<GameNumber> gameNumbers = Arrays.asList(one(), two(), three());

        //when then
        assertDoesNotThrow(() -> PlayerNumbers.createPlayerNumbers(gameNumbers));
    }

    @Test
    @DisplayName("플레이어의 게임 숫자가 세개가 아닐 경우 ArrayIndexOutOfBoundsException을 반환한다.")
    void testCreatePlayerNumbers_withOverSize() {
        //given
        List<GameNumber> gameNumbers = Arrays.asList(one(), two(), three(), four());

        //when
        assertThatThrownBy(() -> PlayerNumbers.createPlayerNumbers(gameNumbers))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class)
                .hasMessageContaining("게임 숫자는 세개의 수로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 게임 숫자가 모두 다른 수가 아닐 경우 NumberFormatException을 반환한다.")
    void testCreatePlayerNumbers_withDuplicateNumber() {
        //given
        List<GameNumber> gameNumbers = Arrays.asList(one(), two(), two());

        //when
        assertThatThrownBy(() -> PlayerNumbers.createPlayerNumbers(gameNumbers))
                .isInstanceOf(NumberFormatException.class)
                .hasMessageContaining("게임 숫자는 모두 다른 수로 구성되어야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 게임 숫자들을 반환한다.")
    void testGetPlayerNumbers() {
        //given
        PlayerNumbers playerNumbers = oneTwoThree();

        //when
        List<GameNumber> playerGameNumbers = playerNumbers.getPlayerNumbers();
        //then
        assertAll(
                () -> assertThat(playerGameNumbers.size()).isEqualTo(3),
                () -> assertThat(playerGameNumbers.get(0).isSame(one())).isTrue(),
                () -> assertThat(playerGameNumbers.get(1).isSame(two())).isTrue(),
                () -> assertThat(playerGameNumbers.get(2).isSame(three())).isTrue()
        );
    }

    @Test
    @DisplayName("플레이어의 게임 숫자 중 특정 숫자와 같고 리스트 내 순서까지 같다.")
    void testIsSamePositionNumber() {
        //given
        PlayerNumbers playerNumbers = oneTwoThree();

        //when
        boolean isSamePositionNumber = playerNumbers.isSamePositionNumber(one(), 0);

        //then
        assertThat(isSamePositionNumber).isTrue();
    }

    @Test
    @DisplayName("플레이어의 게임 숫자 중 특정 숫자와 같지만 리스트 내 순서는 다르다.")
    void testIsAnotherPositionNumber() {
        //given
        PlayerNumbers playerNumbers = oneTwoThree();

        //when
        boolean isSamePositionNumber = playerNumbers.isAnotherPositionNumber(one(), 2);

        //then
        assertThat(isSamePositionNumber).isTrue();
    }

    @Test
    @DisplayName("프레이어의 게임 숫자 중 특정 순서의 숫자를 반환한다.")
    void testgetNumber() {
        //given
        PlayerNumbers playerNumbers = oneTwoThree();

        //when
        GameNumber gameNumber = playerNumbers.getNumber(0);

        //then
        assertThat(gameNumber.isSame(one())).isTrue();
    }
}
