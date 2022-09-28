package baseball.domain.policy;

public enum GameStatusPolicy {
    PLAYING("게임 중"),
    END("게임 종료");

    private String gameStatusDescription;

    GameStatusPolicy(String gameStatusDescription) {
        this.gameStatusDescription = gameStatusDescription;
    }
}
