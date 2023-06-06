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
