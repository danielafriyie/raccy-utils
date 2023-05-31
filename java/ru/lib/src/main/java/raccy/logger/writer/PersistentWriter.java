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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import raccy.utils.Utils;

public class PersistentWriter extends AbstractWriter{

    public PersistentWriter(String outputPath) {
        super(outputPath);
    }

    public PersistentWriter() {
        super();
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
    public void write(String data) {
        String oldData = read();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputPath()));) {
            String newData = oldData + data;
            writer.write(newData);
        } catch (IOException ignore) {

        }
    }

    @Override
    public void close() {

    }
}
