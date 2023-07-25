package org.raccy.captcha;

import java.util.Map;
import java.io.IOException;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface Solver {

    Map<String, Object> submitRequest() throws IOException ;

    Map<String, Object> getSolution() throws IOException ;

    static String imageToBase64(String image) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
