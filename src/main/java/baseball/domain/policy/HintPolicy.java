package baseball.domain.policy;

public enum HintPolicy {
    STRIKE("스트라이크"),
    BALL("볼"),
    NOTHING("낫싱");

    private String hintName;

    HintPolicy(String hintName) {
        this.hintName = hintName;
    }

    public String getHintName() {
        return hintName;
    }
}
