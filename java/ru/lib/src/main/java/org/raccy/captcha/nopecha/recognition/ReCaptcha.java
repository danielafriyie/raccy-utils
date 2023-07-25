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

public class ReCaptcha extends Nopecha {
    private final List<String> images = new ArrayList<>();
    private final List<String> imageURLs = new ArrayList<>();
    private String task = null;
    private String grid = "3x3";

    public ReCaptcha(String apiKey, Logger logger, int retries, int retryWaitTime) {
        super(apiKey, "recaptcha", logger, retries, retryWaitTime);
    }

    public ReCaptcha(String apiKey, Logger logger) {
        this(apiKey, logger, 6, 20);
    }

    public ReCaptcha(String apiKey) {
        this(apiKey, null);
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public void addImage(String... images) throws IOException {
        for (String img : images)
            this.images.add(fileToBase64(img));
    }

    public void addImageURL(String... URLs) {
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
        payload.put("grid", grid);
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
