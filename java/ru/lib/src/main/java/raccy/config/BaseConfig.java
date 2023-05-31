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

package raccy.config;

import java.util.Map;
import java.util.function.Function;

public abstract class BaseConfig {
    private String configPath;
    private Map<String, String> config;

    public BaseConfig(String configPath) throws Exception{
        this.configPath = configPath;
        this.config = load();
    }

    public static Object cast(String item, Function<String, Object> func) {
        if ((func != null) && (item != null))
            return func.apply(item);
        return item;
    }

    public abstract void save() throws Exception;

    public abstract Map<String, String> load() throws Exception;

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
            return cast((String) item, func);
        return default_;
    }

    public String toString() {
        return this.getClass().getName() + "(" + config.toString() + ")";
    }
}
