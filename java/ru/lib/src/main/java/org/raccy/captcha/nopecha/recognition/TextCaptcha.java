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


public class TextCaptcha extends Nopecha {
    private String image = null;  // Base64 encoded image
    private String imageURL = null;

    public TextCaptcha(String apiKey, Logger logger, int retries, int retryWaitTime) {
        super(apiKey, "textcaptcha", logger, retries, retryWaitTime);
    }

    public TextCaptcha(String apiKey, Logger logger) {
        this(apiKey, logger, 6, 20);
    }

    public TextCaptcha(String apiKey) {
        this(apiKey, null);
    }

    public void setImage(String image) throws IOException {
        this.image = fileToBase64(image);
    }

    public void setImageURL(String url) {
        this.imageURL = url;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> submitRequest() throws IOException {
        if ((image == null) && (imageURL == null))
            throw new IllegalStateException("Image not set!");

        Map<String, Object> payload = new HashMap<>();
        payload.put("key", apiKey);
        payload.put("type", type);

        if (image != null)
            payload.put("image_data", new String[]{image});
        else
            payload.put("image_urls", new String[]{imageURL});

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
