import java.nio.file.Paths;

import org.junit.BeforeClass;

import raccy.utils.Utils;

public class BaseTest {
    protected static String baseDir;
    protected static String resourcesDir;

    @BeforeClass
    public static void baseSetUpClass() {
        baseDir = Paths.get("").toAbsolutePath().toString();
        Stream.out.info("baseDir: " + baseDir);
        resourcesDir = Utils.joinPaths(baseDir, "src", "test", "resources");
        Stream.out.info("resourcesDir: " + resourcesDir);
    }
}
