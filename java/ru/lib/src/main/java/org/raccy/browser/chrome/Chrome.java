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

package org.raccy.browser.chrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;

import org.raccy.utils.OS;
import org.raccy.utils.Utils;

public class Chrome {
    public record DebuggerAddress(int port, String address) {}

    public static class DriverBuilder {
        private final List<String> arguments = new ArrayList<>();
        private final List<String> extensions = new ArrayList<>();
        private final int port;
        private final String userDataDir;
        private boolean headless = false;

        public DriverBuilder(int port, String userDataDir) {
            this.port = port;
            this.userDataDir = userDataDir;
        }

        public DriverBuilder setHeadless(boolean headless) {
            this.headless = headless;
            return this;
        }

        public DriverBuilder addArgument(String argument) {
            arguments.add(argument);
            return this;
        }

        public DriverBuilder addExtension(String extension) {
            extensions.add(extension);
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder();
            builder.append("start chrome");
            builder.append(" --remote-debugging-port=").append(port);
            builder.append(" --user-data-dir=").append(String.format("\"%s\"", userDataDir));

            if (headless)
                builder.append(" --headless=new");

            if (extensions.size() > 0)
                builder.append(" --load-extension=").append(String.join(",", extensions));

            for (String arg : arguments) {
                builder.append(" ").append(arg);
            }

            return builder.toString();
        }
    }

    private static final OS os = Utils.getOS();
    private static int port = 9924;
    private static String remoteAddress = "127.0.0.1:";

    private Chrome() {}

    private static synchronized int getPort() {
        int p = port;
        port++;
        return p;
    }

    private static void openBrowserWindows(String cmd) throws IOException {
        Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmd});
    }

    private static void openBrowserLinux(String cmd) throws IOException {
    }

    private static void openBrowserMac(String cmd) throws IOException {
    }

    private static void openBrowser(String cmd) throws IOException {
        switch (os) {
            case MAC -> openBrowserMac(cmd);
            case LINUX -> openBrowserLinux(cmd);
            case WINDOWS -> openBrowserWindows(cmd);
        }
    }

    private static void closeWindowsProcess(String port) {
        String pid = getPID();

        Function<Boolean, Boolean> f = (Boolean kill) -> {
            try {
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec("cmd /c netstat -ano | findstr " + port);

                try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                    String s;
                    while ((s = stdInput.readLine()) != null) {
                        int index = s.lastIndexOf(" ");

                        @SuppressWarnings("all")
                        String sc = s.substring(index, s.length()).strip();

                        if (kill) {
                            if (!sc.equalsIgnoreCase(pid))
                                rt.exec("cmd /c Taskkill /PID " + sc + " /T /F");
                        } else {
                            return true;
                        }
                    }
                }
            } catch (Exception ignore) {
            }

            return false;
        };

        f.apply(true);
    }

    private static void closeMacProcess(String port) {

    }

    private static void closeLinuxProcess(String port) {

    }

    public static String getPID() {
        return String.valueOf(ProcessHandle.current().pid()).strip();
    }

    public static void setRemoteAddress(String address) {
        remoteAddress = address;
    }

    public static DebuggerAddress getDebuggerAddress() {
        int port = getPort();
        String address = remoteAddress + port;
        return new DebuggerAddress(port, address);
    }

    public static ChromeDriver getDriver(DriverBuilder builder, DebuggerAddress debuggerAddress) throws IOException {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        String cmd = builder.build();
        openBrowser(cmd);

        ChromeDriverService service = new ChromeDriverService.Builder().build();
        service.sendOutputTo(OutputStream.nullOutputStream());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("debuggerAddress", debuggerAddress.address());

        return new ChromeDriver(service, options);
    }

    public static void closeBrowser(WebDriver driver, String port) {
        try {
            driver.quit();
        } catch (Exception ignore) {
        }

        switch (os) {
            case MAC -> closeMacProcess(port);
            case LINUX -> closeLinuxProcess(port);
            case WINDOWS -> closeWindowsProcess(port);
        }
    }
}
