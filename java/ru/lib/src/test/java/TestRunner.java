import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestConfig.class);
        for (Failure f : result.getFailures()) {
            System.out.println("\n" + "-".repeat(80) + "\n");
            System.out.println(f.toString());
            System.out.println("\n" + "-".repeat(80) + "\n");
        }
        System.out.println("\n" + "-".repeat(80) + "\n");
        System.out.println("Result == " + result.wasSuccessful());
        System.out.println("\n" + "-".repeat(80) + "\n");
    }
}
