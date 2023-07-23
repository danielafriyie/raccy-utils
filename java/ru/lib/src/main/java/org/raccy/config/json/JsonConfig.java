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

package org.raccy.config.json;

import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import org.raccy.config.BaseConfig;

public class JsonConfig extends BaseConfig {

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
    public Map<String, Object> load() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(getConfigPath()))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, HashMap.class);
        }
    }
}
