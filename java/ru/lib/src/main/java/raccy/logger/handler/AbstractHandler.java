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

package raccy.logger.handler;

import java.nio.file.Paths;

import raccy.utils.Utils;
import raccy.logger.formatter.Formatter;

public abstract class AbstractHandler implements Handler {
    private String outputPath;
    protected Formatter formatter;

    public AbstractHandler(String outputPath, Formatter formatter) {
        this.outputPath = outputPath;
        this.formatter = formatter;
    }

    public AbstractHandler(Formatter formatter) {
        this.outputPath = createLogPath();
        this.formatter = formatter;
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
