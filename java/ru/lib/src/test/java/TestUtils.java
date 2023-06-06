import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;

import raccy.utils.Utils;

public class TestUtils extends BaseTest {
    private static String textFilePath;

    @BeforeClass
    public static void setUpClass() {
        textFilePath = Utils.joinPath(resourcesDir, "foo.txt");
    }

    @Test
    public void testReadFile() throws Exception {
        Stream.out.info("Testing testReadFile ...");
        String foo = Utils.readFile(textFilePath);
        Assert.assertTrue(foo.contains("hello world"));

        List<String> foo2 = Utils.readFile(textFilePath, "\n");
        Assert.assertTrue(foo2.get(0).contains("hello world"));
        Stream.out.success("testReadFile passed!");
    }

    @Test
    public void testGetFileName() throws Exception {
        Stream.out.info("Testing testGetFileName ...");
        String name = Utils.getFileName("foo.txt", resourcesDir);
        Assert.assertEquals(name, Utils.joinPath(resourcesDir, "foo(1).txt"));

        String folderName = Utils.getFileName("config", resourcesDir, true);
        Assert.assertEquals(folderName, Utils.joinPath(resourcesDir, "config(1)"));
        Stream.out.success("testGetFileName passed!");
    }
}
