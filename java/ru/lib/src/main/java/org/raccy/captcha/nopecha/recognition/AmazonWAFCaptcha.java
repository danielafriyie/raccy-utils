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
