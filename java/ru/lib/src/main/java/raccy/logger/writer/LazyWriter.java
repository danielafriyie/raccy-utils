package raccy.logger.writer;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class LazyWriter extends AbstractWriter {
    private BufferedWriter writer;

    public LazyWriter(String outputPath) throws IOException {
        super(outputPath);
        initWriter();
    }

    public LazyWriter() throws IOException {
        super();
        initWriter();
    }

    private void initWriter() throws IOException {
        writer = new BufferedWriter(new FileWriter(getOutputPath()));
    }

    @Override
    public String read() {
        return "";
    }

    @Override
    public void write(String data) {
        try {
            writer.write(data + "\n");
        } catch (IOException ignore) {

        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException ignore) {

        }
    }
}
