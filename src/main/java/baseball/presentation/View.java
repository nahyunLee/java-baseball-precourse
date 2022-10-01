package baseball.presentation;

import baseball.domain.GameResult;
import camp.nextstep.edu.missionutils.Console;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.String.format;

public class View {

    public static int getPlayerRestartAnswer() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return Integer.parseInt(Console.readLine());
    }

    public static String getPlayersNumber() {
        System.out.println("숫자를 입력해주세요 : ");
        return readLine();
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println(getMessage(gameResult));
    }

    private static String getMessage(GameResult gameResult) {
        if (gameResult.isNothing()) {
            return "낫싱";
        }

        if (gameResult.getStrikeNum() == 3) {
            return "3스트라이크 \n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        }

        return getStrikeBallMessage(gameResult.getStrikeNum(), gameResult.getBallNum());
    }

    private static String getStrikeBallMessage(int strikeCount, int ballCount) {
        if (strikeCount == 0) {
            return format("%d볼", ballCount);
        }
        if (ballCount == 0) {
            return format("%d스트라이크", strikeCount);
        }
        return format("%d볼 %d스트라이크", ballCount, strikeCount);
    }
}
