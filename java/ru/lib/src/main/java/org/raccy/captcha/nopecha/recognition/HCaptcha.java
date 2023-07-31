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

package org.raccy.captcha.nopecha.recognition;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import org.apache.logging.log4j.Logger;

import org.raccy.captcha.Method;
import org.raccy.captcha.RestAdapter;
import org.raccy.captcha.nopecha.Nopecha;

public class HCaptcha extends Nopecha {
    private final List<String> images = new ArrayList<>();
    private final List<String> imageURLs = new ArrayList<>();
    private String task = null;

    public HCaptcha(String apiKey, Logger logger, int retries, int retryWaitTime) {
        super(apiKey, "hcaptcha", logger, retries, retryWaitTime);
    }

    public HCaptcha(String apiKey, Logger logger) {
        this(apiKey, logger, 6, 20);
    }

    public HCaptcha(String apiKey) {
        this(apiKey, null);
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void addImage(String... images) throws IOException {
        this.images.clear();
        for (String img : images)
            this.images.add(fileToBase64(img));
    }

    public void addBase64Image(String... images) {
        this.images.clear();
        this.images.addAll(Arrays.asList(images));
    }

    public void addImageURL(String... URLs) {
        this.imageURLs.clear();
        this.imageURLs.addAll(Arrays.asList(URLs));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> submitRequest() throws IOException {
        if (task == null)
            throw new IllegalStateException("Task not set!");

        Map<String, Object> payload = new HashMap<>();
        payload.put("key", apiKey);
        payload.put("type", type);
        payload.put("task", task);
        if (images.size() > 0)
            payload.put("image_data", images);
        else
            payload.put("image_urls", imageURLs);

        String payloadStr = gson.toJson(payload);

        RequestBody body = RequestBody.create(payloadStr, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = adapter.request(Method.POST, BASE_URL, request);) {
            return RestAdapter.toJSON(response, Map.class);
        }
    }
}
