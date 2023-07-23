package org.raccy.config.ini;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Function;

import org.raccy.utils.Utils;
import org.raccy.config.BaseConfig;

public class IniConfig extends BaseConfig {
    private final boolean castNumber;
    private final Map<String, Function<String, Object>> castMap;

    public IniConfig(String configPath, boolean castNumber, Map<String, Function<String, Object>> castMap) throws Exception {
        super(configPath, false);
        this.castNumber = castNumber;
        this.castMap = castMap;
        loadConfig();
    }

    public IniConfig(String configPath, Map<String, Function<String, Object>> castMap) throws Exception {
        this(configPath, true, castMap);
    }

    public IniConfig(String configPath) throws Exception {
        this(configPath, new HashMap<>());
    }

    private boolean castValue(String key, String value, Map<String, Object> map) {
        Function<String, Object> function = castMap.get(key);
        if (function != null) {
            try {
                map.put(key, function.apply(value));
                return true;
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    @Override
    public void save() throws Exception {
    }

    @Override
    public Map<String, Object> load() throws IOException {
        List<String> data = Utils.readFile(getConfigPath(), "\n");
        Map<String, Object> config = new HashMap<>();

        String currentKey = null;
        for (String s : data) {
            if (s.startsWith("[")) {
                currentKey = s.substring(1, s.length() - 1);
                config.put(currentKey, new HashMap<>());
                continue;
            } else if ((currentKey == null) || (s.startsWith("#"))) {
                continue;
            }

            List<String> split = new ArrayList<>(Arrays.asList(s.split("=")));
            String key = split.remove(0).strip();
            String value = String.join("=", split).strip();

            if (!castValue(key, value, ((Map) config.get(currentKey)))) {
                if (castNumber) {
                    try {
                        int v = Integer.parseInt(value.strip());
                        ((Map) config.get(currentKey)).put(key, v);
                    } catch (NumberFormatException ignore) {
                        try {
                            double d = Double.parseDouble(value.strip());
                            ((Map) config.get(currentKey)).put(key, d);
                        } catch (NumberFormatException ignore2) {
                            ((Map) config.get(currentKey)).put(key, value);
                        }
                    }
                } else {
                    ((Map) config.get(currentKey)).put(key, value);
                }
            }


        }

        return config;
    }

    public Object get(String section, String key) {
        return ((Map) get(section)).get(key);
    }
}
