package raccy.logger.colorprint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ColorPrint {

    private ColorPrint() {

    }

    static private synchronized void print(String level, Color color, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s,SSS"));
        String t = now + ":" + level + ":" + msg;
        System.out.println(color + t + Color.RESET);
    }

    public static void info(String msg, Color color) {
        print("INFO", color, msg);
    }

    public static void info(String msg) {
        info(msg, Color.BLUE);
    }

    public static void warning(String msg, Color color) {
        print("WARNING", color, msg);
    }

    public static void warning(String msg) {
        warning(msg, Color.YELLOW);
    }

    public static void success(String msg, Color color) {
        print("SUCCESS", color, msg);
    }

    public static void success(String msg) {
        success(msg, Color.GREEN);
    }

    public static void error(String msg, Color color) {
        print("ERROR", color, msg);
    }

    public static void error(String msg) {
        error(msg, Color.RED);
    }

    public static void error(Exception e) {
        error(e.toString(), Color.RED_BOLD);
    }
}
