package raccy.logger.simple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import raccy.logger.Level;
import raccy.logger.Logger;
import raccy.logger.writer.Writer;

public abstract class BaseLogger implements Logger {
    private Writer writer;

    public BaseLogger(Writer writer) {
        this.writer = writer;
    }

    private synchronized void log(Level level, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s,SSS"));
        String t = now + ":" + level.toString() + ":" + msg;
        System.out.println(t);
        writer.write(t);
    }

    @Override
    public void info(String msg) {
        log(Level.INFO, msg);
    }

    @Override
    public void warning(String msg) {
        log(Level.WARNING, msg);
    }

    @Override
    public void success(String msg) {
        log(Level.SUCCESS, msg);
    }

    @Override
    public void error(String msg) {
        log(Level.ERROR, msg);
    }

    @Override
    public void error(Throwable e) {
        error(getErrorMsg(e));
    }

    public void close() {
        writer.close();
    }
}
