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

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class LazyWriter extends AbstractWriter {
    private BufferedWriter writer;

    public LazyWriter(String outputPath) throws IOException {
        super(outputPath);
        initWriter();
    }

    public LazyWriter() throws IOException {
        super();
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
    public void write(String data) {
        try {
            writer.write(data + "\n");
        } catch (IOException ignore) {

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
