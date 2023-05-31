package raccy.logger;


public interface Logger {

    void info(String msg);

    void warning(String msg);

    void success(String msg);

    void error(String msg);

    void error(Exception e);
}
