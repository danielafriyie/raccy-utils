package raccy.logger.simple;

import java.io.IOException;

import raccy.logger.writer.LazyWriter;

public class LazyLogger extends BaseLogger {

    public LazyLogger() throws IOException {
        super(new LazyWriter());
    }

    public LazyLogger(String logPath) throws IOException {
        super(new LazyWriter(logPath));
    }
}
