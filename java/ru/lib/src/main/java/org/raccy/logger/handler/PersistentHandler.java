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

package org.raccy.logger.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.raccy.utils.Utils;
import org.raccy.logger.Level;
import org.raccy.logger.formatter.Formatter;

public class PersistentHandler extends AbstractHandler {

    public PersistentHandler(String outputPath, Formatter formatter) {
        super(outputPath, formatter);
    }

    public PersistentHandler() {
        super(new Formatter("yyyy-MM-dd H:m:s,SSS"));
    }

    @Override
    public String read() {
        try {
            return Utils.readFile(getOutputPath());
        } catch (IOException ignore) {
            return "";
        }
    }

    @Override
    public void write(Level level, String data) {
        synchronized (this) {
            String msg = formatter.formatMessage(level, data);
            System.out.println(msg);

            String oldData = read();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputPath()));) {
                String newData = oldData + msg;
                writer.write(newData);
            } catch (IOException ignore) {

            }
        }

    }

    @Override
    public void close() {

    }
}
