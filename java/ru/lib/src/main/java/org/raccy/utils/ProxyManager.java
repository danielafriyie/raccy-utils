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

package org.raccy.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProxyManager {
    public record Proxy(boolean isAuth, String proxy) {
    }

    private int index = 0;
    private final String path;
    private final String outputDir;
    private final List<Proxy> proxyList = new ArrayList<>();

    public ProxyManager(String path, String outputDir) {
        this.path = path;
        this.outputDir = outputDir;
        this.load();
    }

    private String createProxyExtension(String host, String port, String user, String password) throws IOException {
        String manifestJson = """
                {
                    "version": "1.0.0",
                    "manifest_version": 2,
                    "name": "Chrome Proxy",
                    "permissions": [
                        "proxy",
                        "tabs",
                        "unlimitedStorage",
                        "storage",
                        "<all_urls>",
                        "webRequest",
                        "webRequestBlocking"
                    ],
                    "background": {
                        "scripts": ["background.js"]
                    },
                    "minimum_chrome_version":"22.0.0"
                }
                """;

        String backgroundJs = String.format("""
                var config = {
                        mode: "fixed_servers",
                        rules: {
                        singleProxy: {
                            scheme: "http",
                            host: "%s",
                            port: parseInt(%s)
                        },
                        bypassList: ["localhost"]
                        }
                    };

                chrome.proxy.settings.set({value: config, scope: "regular"}, function() {});

                function callbackFn(details) {
                    return {
                        authCredentials: {
                            username: "%s",
                            password: "%s"
                        }
                    };
                }

                chrome.webRequest.onAuthRequired.addListener(
                            callbackFn,
                            {urls: ["<all_urls>"]},
                            ['blocking']
                );
                """, host, port, user, password);

        String fp = Utils.getFileName("proxy_ext", outputDir, true);
        Utils.makeDir(fp);

        String manifestPath = Utils.joinPath(fp, "manifest.json");
        Utils.write(manifestPath, manifestJson);

        String backgroundPath = Utils.joinPath(fp, "background.js");
        Utils.write(backgroundPath, backgroundJs);

        return fp;
    }

    private void load() {
        try {
            List<String> list = Utils.readFile(path, "\n");
            for (String s : list) {
                String[] split = s.split(":");
                if (split.length == 2) {
                    proxyList.add(new Proxy(false, s.strip()));
                } else if (split.length == 4) {
                    String host, port, user, pass;
                    host = split[0].strip();
                    port = split[1].strip();
                    user = split[2].strip();
                    pass = split[3].strip();
                    String path = createProxyExtension(host, port, user, pass);
                    proxyList.add(new Proxy(true, path));
                }
            }
        } catch (IOException ignore) {
        }
    }

    private Proxy getProxy() {
        Proxy proxy = proxyList.get(index);
        index++;
        return proxy;
    }

    public synchronized Proxy get() {
        if (proxyList.size() == 0)
            return null;

        try {
            return getProxy();
        } catch (IndexOutOfBoundsException ignore) {
            index = 0;
            return getProxy();
        }

    }

    public void clean() {
        Utils.removeDir(outputDir, Utils.getOS());
    }
}
