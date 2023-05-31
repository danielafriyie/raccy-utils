package raccy.logger.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import raccy.utils.Utils;

public class PersistentWriter extends AbstractWriter{

    public PersistentWriter(String outputPath) {
        super(outputPath);
    }

    public PersistentWriter() {
        super();
    }

    @Override
    public String read() {
        try {
            return Utils.readFile(getOutputPath());
        } catch (IOException ignore) {
            return "";
        }
    }

    @Override
    public void write(String data) {
        String oldData = read();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputPath()));) {
            String newData = oldData + data;
            writer.write(newData);
        } catch (IOException ignore) {

        }
    }

    @Override
    public void close() {

    }
}
