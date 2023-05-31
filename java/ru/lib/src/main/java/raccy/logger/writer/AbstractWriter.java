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

package raccy.logger.writer;

import java.nio.file.Paths;

import raccy.utils.Utils;

public abstract class AbstractWriter implements Writer {
    private String outputPath;

    public AbstractWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    public AbstractWriter() {
        this.outputPath = createLogPath();
    }

    public static String createLogPath() {
        String baseDir = Paths.get("").toAbsolutePath().toString();
        String logDir = Utils.joinPath(baseDir, "logs");

        Utils.makeDir(logDir);

        return Utils.joinPath(logDir, "log.log");
    }

    String getOutputPath() {
        return outputPath;
    }
}
