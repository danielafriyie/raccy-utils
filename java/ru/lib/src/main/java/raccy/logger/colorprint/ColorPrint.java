package raccy.logger.colorprint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import raccy.logger.Logger;

public class ColorPrint implements Logger {

    private synchronized void print(String level, Color color, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s,SSS"));
        String t = now + ":" + level + ":" + msg;
        System.out.println(color + t + Color.RESET);
    }

    public void info(String msg, Color color) {
        print("INFO", color, msg);
    }

    @Override
    public void info(String msg) {
        info(msg, Color.BLUE);
    }

    public void warning(String msg, Color color) {
        print("WARNING", color, msg);
    }

    @Override
    public void warning(String msg) {
        warning(msg, Color.YELLOW);
    }

    public void success(String msg, Color color) {
        print("SUCCESS", color, msg);
    }

    @Override
    public void success(String msg) {
        success(msg, Color.GREEN);
    }

    public void error(String msg, Color color) {
        print("ERROR", color, msg);
    }

    @Override
    public void error(String msg) {
        error(msg, Color.RED);
    }

    @Override
    public void error(Throwable e) {
        error(getErrorMsg(e), Color.RED_BOLD);
    }
}
