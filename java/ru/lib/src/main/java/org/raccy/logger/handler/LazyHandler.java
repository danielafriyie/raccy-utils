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

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import org.raccy.logger.Level;
import org.raccy.logger.formatter.Formatter;

public class LazyHandler extends AbstractHandler {
    private BufferedWriter writer;

    public LazyHandler(String outputPath, Formatter formatter) throws IOException {
        super(outputPath, formatter);
        initWriter();
    }

    public LazyHandler() throws IOException {
        super(new Formatter("yyyy-MM-dd H:m:s,SSS"));
        initWriter();
    }

    private void initWriter() throws IOException {
        writer = new BufferedWriter(new FileWriter(getOutputPath()));
    }

    @Override
    public String read() {
        return "";
    }

    @Override
    public void write(Level level, String data) {
        synchronized(this) {
            try {
                String msg = formatter.formatMessage(level, data);
                System.out.println(msg);
                writer.write(msg + "\n");
            } catch (IOException ignore) {

            }
        }

    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException ignore) {

        }
    }
}
