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

package org.raccy.config;

import java.util.Map;
import java.util.function.Function;

public abstract class BaseConfig {
    private final String configPath;
    private Map<String, Object> config;

    public BaseConfig(String configPath, boolean loadConfig) throws Exception {
        this.configPath = configPath;
        if (loadConfig)
            this.config = load();
    }

    public BaseConfig(String configPath) throws Exception {
        this(configPath, true);
    }

    protected void loadConfig() throws Exception {
        this.config = load();
    }

    public abstract void save() throws Exception;

    public abstract Map<String, Object> load() throws Exception;

    public Map<String, Object> getConfig() {
        return config;
    }

    public String getConfigPath() {
        return configPath;
    }

    public synchronized void put(String key, Object value) {
        config.put(key, value);
    }

    public synchronized <T> T get(String key, Class<T> cast) {
        Object item = config.get(key);
        return cast.cast(item);
    }

    public Object get(String key) {
        return get(key, Object.class);
    }

    public Object get(String key, Object default_) {
        Object item = get(key);
        return item != null ? item : default_;
    }

    public Number getNumber(String key, Function<String, Number> function) {
        return function.apply((String) get(key));
    }

    public int getInt(String key) {
        return (int) getNumber(key, Integer::parseInt);
    }

    public double getDouble(String key) {
        return (double) getNumber(key, Double::parseDouble);
    }

    public String toString() {
        return this.getClass().getName() + "(" + config.toString() + ")";
    }
}
