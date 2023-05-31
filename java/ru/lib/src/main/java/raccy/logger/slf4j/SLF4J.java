package raccy.logger.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J implements raccy.logger.Logger{
    @Override
    public void info(String msg) {

    }

    @Override
    public void warning(String msg) {

    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void error(Exception e) {

    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("SampleLogger");
        logger.info("Hi This is my first SLF4J program");
    }
}
