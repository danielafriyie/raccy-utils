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

package raccy.logger;

import java.util.Arrays;

public interface Logger {

    void info(String msg);

    void warning(String msg);

    void success(String msg);

    void error(String msg);

    void error(Throwable e);

    default String getErrorMsg(Throwable e) {
        String msg = e.toString();
        StringBuilder builder = new StringBuilder();

        Arrays.stream(e.getStackTrace()).forEach((elm) -> {
            String line = elm.toString();
            String method = elm.getMethodName();
            String className = elm.getClassName();
            builder.append(line)
                    .append("\n")
                    .append("Class: ")
                    .append(className)
                    .append("\n")
                    .append("Method: ")
                    .append(method)
                    .append("\n")
                    .append(msg)
                    .append("\n");
        });

        return builder.toString();
    }
}
