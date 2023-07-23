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

import java.util.Map;
import java.util.HashMap;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;

import org.raccy.utils.Utils;
import org.raccy.config.ini.IniConfig;
import org.raccy.config.text.TextConfig;
import org.raccy.config.json.JsonConfig;

public class TestConfig extends BaseTest {
    private static TextConfig textConfig;
    private static JsonConfig jsonConfig;
    private static IniConfig iniConfig;

    @BeforeClass
    public static void setUpClass() throws Exception {
        textConfig = new TextConfig(Utils.joinPaths(resourcesDir, "config", "config.txt"));
        jsonConfig = new JsonConfig(Utils.joinPaths(resourcesDir, "config", "config.json"));
        iniConfig = new IniConfig(Utils.joinPaths(resourcesDir, "config", "config.ini"));
    }

    @Test
    public void testGet() {
        Stream.out.info("Testing testGet ...");
        Assert.assertEquals(textConfig.get("email"), "test@mail.com");
        Assert.assertEquals(textConfig.get("password"), "foobar123@");

        Assert.assertEquals(jsonConfig.get("firstName"), "John");
        Assert.assertEquals(jsonConfig.get("lastName"), "Smith");
        Assert.assertEquals(jsonConfig.get("email"), "john@smith.com");

        Assert.assertEquals(textConfig.get("its not there", "hi, i'm not there"), "hi, i'm not there");
        Assert.assertEquals(jsonConfig.get("its not there", "hi, i'm not there"), "hi, i'm not there");

        int tID = textConfig.getInt("integer");
        double tND = textConfig.getDouble("double");
        Assert.assertEquals(tID, 10);
        Assert.assertEquals(tND, 10.23, 0);

        int jID = jsonConfig.getInt("integer");
        double jND = jsonConfig.getDouble("double");
        Assert.assertEquals(jID, 10);
        Assert.assertEquals(jND, 10.23, 0);


        Map<String, Object> numbers = iniConfig.getSection("NUMBERS");
        Assert.assertEquals(numbers.get("integer"), "10");
        Assert.assertEquals(numbers.get("double"), "10.23");
        int iID = Integer.parseInt((String) numbers.get("integer"));
        double iND = Double.parseDouble((String) numbers.get("double"));
        Assert.assertEquals(iID, 10);
        Assert.assertEquals(iND, 10.23, 0);
        Stream.out.success("testGet passed!");
    }

    @Test
    public void testPut() {
        Stream.out.info("Testing testPut ...");
        String[][] keys = new String[][]{
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
        Stream.out.success("testPut passed!");
    }
}
