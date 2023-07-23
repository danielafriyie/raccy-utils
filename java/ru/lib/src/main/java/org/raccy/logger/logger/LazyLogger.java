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

package org.raccy.logger.logger;

import java.io.IOException;

import org.raccy.logger.formatter.Formatter;
import org.raccy.logger.handler.LazyHandler;

public class LazyLogger extends BaseLogger {

    public LazyLogger() throws IOException {
        super(new LazyHandler());
    }

    public LazyLogger(String logPath, Formatter formatter) throws IOException {
        super(new LazyHandler(logPath, formatter));
    }
}
