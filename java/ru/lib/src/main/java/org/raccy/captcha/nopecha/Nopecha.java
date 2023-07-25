package org.raccy.captcha.nopecha;

import java.util.Map;

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

    protected final String BASE_URL = "https://api.nopecha.com/";

    public Nopecha(String apiKey, String type, Logger logger) {
        this.apiKey = apiKey;
        this.type = type;
        this.logger = logger != null ? logger : LogManager.getLogger();
        this.adapter = new RestAdapter(logger);
    }

    @Override
    public Map<String, Object> getSolution() {
        return null;
    }
}
