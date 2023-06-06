/*
Copyright 2021 Daniel Afriyie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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
