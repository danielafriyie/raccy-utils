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

import org.raccy.logger.Level;
import org.raccy.logger.Logger;
import org.raccy.logger.handler.Handler;

public class BaseLogger implements Logger {
    private Handler handler;

    public BaseLogger(Handler handler) {
        this.handler = handler;
    }

    private void log(Level level, String msg) {
        handler.write(level, msg);
    }

    @Override
    public void info(String msg) {
        log(Level.INFO, msg);
    }

    @Override
    public void warning(String msg) {
        log(Level.WARNING, msg);
    }

    @Override
    public void success(String msg) {
        log(Level.SUCCESS, msg);
    }

    @Override
    public void error(String msg) {
        log(Level.ERROR, msg);
    }

    @Override
    public void error(Throwable e) {
        error(getErrorMsg(e));
    }

    public void close() {
        handler.close();
    }
}
