package org.raccy.captcha;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.google.gson.Gson;

public class RestAdapter {
    protected final Logger logger;
    protected final OkHttpClient client;

    public RestAdapter(Logger logger) {
        this.logger = logger != null ? logger : LogManager.getLogger();
        this.client = new OkHttpClient();
    }

    public Response request(Method method, String url, Request request) throws IOException {
        logger.debug(String.format("Making request, method: %s, url: %s", method, url));
        return client.newCall(request).execute();
    }

    public static <T> T toJSON(Response response, Class<T> klass) throws IOException {
        @SuppressWarnings("all")
        String body = response.body().string();
        return new Gson().fromJson(body, klass);
    }
}

