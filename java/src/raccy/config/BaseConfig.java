package raccy.config;

import java.util.Map;
import java.io.IOException;
import java.util.function.Function;

public abstract class BaseConfig {
    private String configPath;
    private Map<String, String> config;

    public static Object cast(String item, Function<String, Object> func) {
        if (func != null)
            return func.apply(item);
        return item;
    }

    public BaseConfig(String configPath) throws IOException{
        this.configPath = configPath;
        this.config = load();
    }

    public abstract void save() throws IOException;

    public abstract Map<String, String> load() throws IOException;

    public Map<String, String> getConfig() {
        return config;
    }

    public String getConfigPath() {
        return configPath;
    }

    public synchronized void put(String key, String value) {
        config.put(key, value);
    }

    public synchronized String get(String key) {
        return config.get(key);
    }

    public Object get(String key, Object default_) {
        String item = get(key);
        if (item != null)
            return item;
        return default_;
    }

    public Object get(String key, Object default_, Function<String, Object> func) {
        Object item = get(key, default_);
        if (item != null)
            return func.apply((String) item);
        return default_;
    }

    public String toString() {
        return this.getClass().getName() + "(" + config.toString() + ")";
    }
}
