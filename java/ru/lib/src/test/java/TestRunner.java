import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestConfig.class);
        for (Failure f : result.getFailures()) {
            Stream.out.info("\n" + "-".repeat(80) + "\n");
            Stream.out.info(f.toString());
            Stream.out.info("\n" + "-".repeat(80) + "\n");
        }
        Stream.out.info("\n" + "-".repeat(80) + "\n");
        Stream.out.info("Result == " + result.wasSuccessful());
        Stream.out.info("\n" + "-".repeat(80) + "\n");
    }
}
