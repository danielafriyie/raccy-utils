package raccy.config;

import java.util.Map;
import java.util.function.Function;

public abstract class BaseConfig {
    private String configPath;
    private Map<String, String> config;

    public static Object cast(String item, Function<String, Object> func) {
        if (func != null)
            return func.apply(item);
        return item;
    }

    public BaseConfig(String configPath) {
        this.configPath = configPath;
        this.config = load();
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public abstract void save();

    public abstract Map<String, String> load();

    public String get(String key) {
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
        return config.toString();
    }
}
