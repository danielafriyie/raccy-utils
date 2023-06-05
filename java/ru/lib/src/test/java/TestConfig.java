import java.nio.file.Paths;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;

import raccy.utils.Utils;
import raccy.config.TextConfig;
import raccy.config.JsonConfig;

public class TestConfig {
    public static String baseDir;
    private static TextConfig textConfig;
    private static JsonConfig jsonConfig;

    @BeforeClass
    public static void setupClass() throws Exception {
        baseDir = Paths.get("").toAbsolutePath().toString();
        textConfig = new TextConfig(Utils.joinPaths(baseDir, "src", "test", "resources", "config", "config.txt"));
        jsonConfig = new JsonConfig(Utils.joinPaths(baseDir, "src", "test", "resources", "config", "config.json"));
    }

    @Test
    public void testGet() {
        Assert.assertEquals(textConfig.get("email"), "test@mail.com");
        Assert.assertEquals(textConfig.get("password"), "foobar123@");

        Assert.assertEquals(jsonConfig.get("firstName"), "John");
        Assert.assertEquals(jsonConfig.get("lastName"), "Smith");
        Assert.assertEquals(jsonConfig.get("email"), "john@smith.com");

        Assert.assertEquals(textConfig.get("its not there", "hi, i'm not there"), "hi, i'm not there");
        Assert.assertEquals(jsonConfig.get("its not there", "hi, i'm not there"), "hi, i'm not there");

        Assert.assertEquals(textConfig.get("number", null, Integer::parseInt), 10);
        Assert.assertEquals(jsonConfig.get("number", null, Integer::parseInt), 10);
    }

    @Test
    public void testPut() {
        String[][] keys = new String[][] {
                {"test1", "Testy1"},
                {"test2", "Testy2"},
                {"test3", "Testy3"},
                {"test4", "Testy4"},
                {"test5", "Testy5"}
        };

        for (String[] arr : keys) {
            textConfig.put(arr[0], arr[1]);
            jsonConfig.put(arr[0], arr[1]);
        }

        for (String[] arr : keys) {
            Assert.assertEquals(textConfig.get(arr[0]), arr[1]);
            Assert.assertEquals(jsonConfig.get(arr[0]), arr[1]);
        }
    }
}
