package raccy.logger.writer;

import java.nio.file.Paths;

import raccy.utils.Utils;

public abstract class AbstractWriter implements Writer {
    private String outputPath;

    public AbstractWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    public AbstractWriter() {
        this.outputPath = createLogPath();
    }

    public static String createLogPath() {
        String baseDir = Paths.get("").toAbsolutePath().toString();
        String logDir = Utils.joinPath(baseDir, "logs");

        Utils.makeDir(logDir);

        return Utils.joinPath(logDir, "log.log");
    }

    String getOutputPath() {
        return outputPath;
    }
}
