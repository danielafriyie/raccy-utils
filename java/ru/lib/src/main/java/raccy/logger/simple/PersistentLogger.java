package raccy.logger.simple;

import raccy.logger.writer.PersistentWriter;

public class PersistentLogger extends BaseLogger {

    public PersistentLogger(String logPath) {
        super(new PersistentWriter(logPath));
    }

    public PersistentLogger() {
        super(new PersistentWriter());
    }
}
