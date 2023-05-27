package raccy.config;

import java.util.Map;

public class JsonConfig extends BaseConfig{
    public JsonConfig(String configPath) {
        super(configPath);
    }

    @Override
    public void save() {

    }

    @Override
    public Map<String, String> load() {
        return null;
    }
}
