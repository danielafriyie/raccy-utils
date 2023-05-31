package raccy.logger;

public enum Level {
    INFO("INFO"),
    WARNING("WARNING"),
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private final String level;

    Level(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
