package raccy.logger;

import java.util.Arrays;

public interface Logger {

    void info(String msg);

    void warning(String msg);

    void success(String msg);

    void error(String msg);

    void error(Exception e);

    default String getErrorMsg(Exception e) {
        String msg = e.toString();
        StringBuilder builder = new StringBuilder();

        Arrays.stream(e.getStackTrace()).forEach((elm) -> {
            String line = elm.toString();
            String method = elm.getMethodName();
            String className = elm.getClassName();
            builder.append(line)
                    .append("\n")
                    .append("Class: ")
                    .append(className)
                    .append("\n")
                    .append("Method: ")
                    .append(method)
                    .append("\n")
                    .append(msg)
                    .append("\n");
        });

        return builder.toString();
    }
}
