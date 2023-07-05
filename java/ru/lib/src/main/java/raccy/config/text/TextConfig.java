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

package raccy.config.text;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import raccy.utils.Utils;
import raccy.config.BaseConfig;

public class TextConfig extends BaseConfig {

    public TextConfig(String configPath) throws Exception {
        super(configPath);
    }

    public TextConfig() throws Exception {
        this("config.txt");
    }

    @Override
    public void save() throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(getConfigPath()))) {
            StringBuilder builder = new StringBuilder();
            Map<String, String> config = getConfig();
            config.forEach((key, value) -> builder.append(key).append("=").append(value).append("\n"));
            String output = builder.toString();
            br.write(output);
        }
    }

    @Override
    public Map<String, String> load() throws IOException {
        List<String> data = Utils.readFile(getConfigPath(), "\n");
        HashMap<String, String> config = new HashMap<>();

        for (String line : data) {
            String[] split = line.split("=");
            String key = split[0];
            String value = String.join("=", Arrays.copyOfRange(split, 1, split.length));
            config.put(key, value);
        }

        return config;
    }
}
