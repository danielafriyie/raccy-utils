package org.raccy.captcha;

import java.util.Map;
import java.io.IOException;

public interface Solver {

    Map<String, Object> submitRequest() throws IOException ;

    Map<String, Object> getSolution() throws IOException ;
}
