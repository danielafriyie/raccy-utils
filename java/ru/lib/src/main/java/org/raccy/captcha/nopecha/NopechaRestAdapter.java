package org.raccy.captcha.nopecha;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.google.gson.Gson;

import org.raccy.captcha.RestAdapter;

public class NopechaRestAdapter extends RestAdapter {

    public NopechaRestAdapter(Logger logger) {
        super(logger);
    }
}
