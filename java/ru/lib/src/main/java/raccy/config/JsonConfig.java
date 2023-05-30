package raccy.config;

import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

public class JsonConfig extends BaseConfig{
    public JsonConfig(String configPath) throws Exception {
        super(configPath);
    }

    public JsonConfig() throws Exception {
        this(new File("config.json").getAbsolutePath());
    }

    @Override
    public void save() throws IOException {
        try (FileWriter writer = new FileWriter(getConfigPath())) {
            Gson gson = new Gson();
            gson.toJson(getConfig(), writer);
        }
    }

    @Override
    public Map<String, String> load() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(getConfigPath()))) {
            Gson gson = new Gson();
            HashMap config = gson.fromJson(reader, HashMap.class);
            return config;
        }
    }
}
