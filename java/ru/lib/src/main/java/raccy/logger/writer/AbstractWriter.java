package raccy.logger.writer;

public abstract class AbstractWriter implements Writer {
    private String outputPath;

    public AbstractWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    String getOutputPath() {
        return outputPath;
    }
}
