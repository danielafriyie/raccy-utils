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
    private static final Gson gson = new Gson();

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
        return gson.fromJson(body, klass);
    }
}

