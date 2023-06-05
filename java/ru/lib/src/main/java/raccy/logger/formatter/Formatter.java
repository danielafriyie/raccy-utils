package raccy.logger.formatter;

import raccy.logger.Level;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private String pattern;

    public Formatter(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String formatMessage(Level level, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
        return now + ":" + level.toString() + ":" + msg;
    }
}
