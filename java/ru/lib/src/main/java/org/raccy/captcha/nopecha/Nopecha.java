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

package org.raccy.captcha.nopecha;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.raccy.captcha.Solver;
import org.raccy.captcha.Method;
import org.raccy.captcha.RestAdapter;

public abstract class Nopecha implements Solver {
    protected final String apiKey;
    protected final String type;
    protected final Logger logger;
    protected final RestAdapter adapter;
    protected final int retries;
    protected final int retryWaitTime;  // in seconds
    protected final int retryWaitTimeInMillis;

    protected final String BASE_URL = "https://api.nopecha.com/";
    protected static final Gson gson = new Gson();
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Nopecha(String apiKey, String type, Logger logger, int retries, int retryWaitTime) {
        this.apiKey = apiKey;
        this.type = type;
        this.logger = logger != null ? logger : LogManager.getLogger();
        this.retries = retries;
        this.retryWaitTime = retryWaitTime;
        this.retryWaitTimeInMillis = retryWaitTime * 1000;
        this.adapter = new RestAdapter(this.logger);
    }

    @Override
    @SuppressWarnings("all")
    public Map<String, Object> getSolution() throws IOException {
        logger.debug("Submitting request ...");
        Map<String, Object> results = submitRequest();
        logger.debug("Request submitted, response: " + results);
        logger.debug("Getting captcha results ...");

        String requestID = (String) results.get("data");
        String url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addQueryParameter("key", apiKey)
                .addQueryParameter("id", requestID)
                .build()
                .toString();

        Request request = new Request.Builder().url(url).build();

        for (int i = 0; i < retries; i++) {
            logger.debug(String.format("Getting captcha results attempt %s ...", (i + 1)));
            try (Response response = adapter.request(Method.GET, BASE_URL, request);) {
                Map<String, Object> captchaResults = RestAdapter.toJSON(response, Map.class);
                logger.debug(String.format("Captcha results %s", captchaResults));

                Object error = captchaResults.get("error");
                if (error != null) {
                    String message = (String) captchaResults.get("message");
                    if (message.toLowerCase().contains("incomplete")) {
                        try {
                            logger.debug(String.format("Waiting for %s seconds to make the next request ...", retryWaitTime));
                            Thread.sleep(retryWaitTimeInMillis);
                        } catch (InterruptedException ignore) {

                        }
                        continue;
                    }
                    break;
                }

                return captchaResults;
            }
        }

        logger.warn("Failed to get captcha results!");
        return null;
    }
}
