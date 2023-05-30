package raccy.config;

import java.util.Map;
import java.io.IOException;

public class JsonConfig extends BaseConfig{
    public JsonConfig(String configPath) throws IOException {
        super(configPath);
    }

    public JsonConfig() throws IOException {
        this("config.json");
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public Map<String, String> load() throws IOException {
        return null;
    }
}
