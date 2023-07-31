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
import java.util.HashMap;
import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import org.apache.logging.log4j.Logger;

import org.raccy.captcha.Method;
import org.raccy.captcha.RestAdapter;
import org.raccy.captcha.nopecha.Nopecha;


public class AmazonWAFCaptcha extends Nopecha {
    private String audio = null;  // Base64 encoded audio

    public AmazonWAFCaptcha(String apiKey, Logger logger, int retries, int retryWaitTime) {
        super(apiKey, "awscaptcha", logger, retries, retryWaitTime);
    }

    public AmazonWAFCaptcha(String apiKey, Logger logger) {
        this(apiKey, logger, 6, 20);
    }

    public AmazonWAFCaptcha(String apiKey) {
        this(apiKey, null);
    }

    public void setAudio(String audio) throws IOException {
        this.audio = fileToBase64(audio);
    }

    public void setBase64Audio(String audio) {
        this.audio = audio;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> submitRequest() throws IOException {
        if (audio == null)
            throw new IllegalStateException("Audio not set!");

        Map<String, Object> payload = new HashMap<>();
        payload.put("key", apiKey);
        payload.put("type", type);
        payload.put("audio_data", new String[]{audio});

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
