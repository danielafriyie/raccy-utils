package raccy.logger.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J implements raccy.logger.Logger {
    private Logger logger;

    public SLF4J() {
        this.logger = LoggerFactory.getLogger(SLF4J.class);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void warning(String msg) {
        logger.warn(msg);
    }

    @Override
    public void success(String msg) {
        logger.info(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(Throwable e) {
        logger.error(getErrorMsg(e));
    }
}
