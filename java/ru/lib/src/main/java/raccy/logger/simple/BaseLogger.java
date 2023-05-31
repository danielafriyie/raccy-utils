package raccy.logger.simple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import raccy.utils.Utils;
import raccy.logger.Logger;
import raccy.logger.writer.Writer;
import raccy.logger.writer.LazyWriter;

public abstract class BaseLogger implements Logger {
    private String logPath;
    private Writer writer;

    public BaseLogger(String logPath, Writer writer) {
        this.logPath = logPath;
        this.writer = writer;
    }

    public BaseLogger() throws IOException {
        createLogPath();
        this.writer = new LazyWriter(logPath);
    }

    private void createLogPath() {
        String baseDir = Paths.get("").toAbsolutePath().toString();
        String logDir = Utils.joinPath(baseDir, "logs");
        logPath = Utils.joinPath(logDir, "log.log");

        Utils.makeDir(logDir);
    }

    private synchronized void log(String level, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s,SSS"));
        String t = now + ":" + level + ":" + msg;
        System.out.println(t);
        writer.write(t + "\n");
    }

    @Override
    public void info(String msg) {
        log("INFO", msg);
    }

    @Override
    public void warning(String msg) {
        log("WARNING", msg);
    }

    @Override
    public void success(String msg) {
        log("SUCCESS", msg);
    }

    @Override
    public void error(String msg) {
        log("ERROR", msg);
    }

    @Override
    public void error(Throwable e) {
        error(getErrorMsg(e));
    }

    public void close() {
        writer.close();
    }
}
