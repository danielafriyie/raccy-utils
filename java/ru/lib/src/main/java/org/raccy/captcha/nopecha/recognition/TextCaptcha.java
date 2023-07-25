package org.raccy.captcha.nopecha.recognition;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.FormBody;
import okhttp3.MediaType;

import org.raccy.captcha.Method;
import org.raccy.captcha.RestAdapter;
import org.raccy.captcha.nopecha.Nopecha;


public class TextCaptcha extends Nopecha {
    private String image = null;  // Base64 encoded image
    private String imageURL = null;
    private static final Gson gson = new Gson();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public TextCaptcha(String apiKey, Logger logger) {
        super(apiKey, "textcaptcha", logger);
    }

    public void setImage(String image) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        this.image = Base64.getEncoder().encodeToString(bytes);
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

        Gson gson = new Gson();
        String payloadStr = gson.toJson(payload);


        logger.debug("Submitting request ...");
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
