package org.raccy.captcha;

import java.util.Map;

public interface Solver {

    Map<String, Object> submitRequest();

    Map<String, Object> getSolution();
}
