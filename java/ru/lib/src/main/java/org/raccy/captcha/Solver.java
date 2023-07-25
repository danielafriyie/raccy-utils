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

import java.util.Map;
import java.io.IOException;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface Solver {

    Map<String, Object> submitRequest() throws IOException ;

    Map<String, Object> getSolution() throws IOException ;

    default String fileToBase64(String file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
