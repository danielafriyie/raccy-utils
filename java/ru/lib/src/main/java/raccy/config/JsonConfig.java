package raccy.config;

import java.util.Map;
import java.io.IOException;

public class JsonConfig extends BaseConfig{
    public JsonConfig(String configPath) throws IOException {
        super(configPath);
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public Map<String, String> load() throws IOException {
        return null;
    }
}
