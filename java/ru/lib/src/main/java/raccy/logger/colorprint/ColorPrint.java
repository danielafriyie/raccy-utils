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

package raccy.logger.colorprint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import raccy.logger.Level;
import raccy.logger.Logger;

public class ColorPrint implements Logger {

    private synchronized void print(Level level, Color color, String msg) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s,SSS"));
        String t = now + ":" + level.toString() + ":" + msg;
        System.out.println(color + t + Color.RESET);
    }

    public void info(String msg, Color color) {
        print(Level.INFO, color, msg);
    }

    @Override
    public void info(String msg) {
        info(msg, Color.BLUE);
    }

    public void warning(String msg, Color color) {
        print(Level.WARNING, color, msg);
    }

    @Override
    public void warning(String msg) {
        warning(msg, Color.YELLOW);
    }

    public void success(String msg, Color color) {
        print(Level.SUCCESS, color, msg);
    }

    @Override
    public void success(String msg) {
        success(msg, Color.GREEN);
    }

    public void error(String msg, Color color) {
        print(Level.ERROR, color, msg);
    }

    @Override
    public void error(String msg) {
        error(msg, Color.RED);
    }

    @Override
    public void error(Throwable e) {
        error(getErrorMsg(e), Color.RED_BOLD);
    }
}
