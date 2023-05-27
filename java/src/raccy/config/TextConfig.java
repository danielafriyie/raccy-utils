package raccy.config;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TextConfig extends BaseConfig {

    public TextConfig(String configPath) throws IOException {
        super(configPath);
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
        try (BufferedReader br = new BufferedReader(new FileReader(getConfigPath()))) {
            HashMap<String, String> config = new HashMap<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split("=");
                String key = split[0];
                String value = String.join("=", Arrays.copyOfRange(split, 1, split.length));
                config.put(key, value);
            }

            return config;
        }
    }
}
