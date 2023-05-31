package raccy.logger.writer;

public interface Writer {

    String read();

    void write(String data);

    void close();
}
