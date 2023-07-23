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

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;

public class Utils {

    private Utils() {

    }

    public static String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }

            return builder.toString();
        }
    }

    public static List<String> readFile(String fileName, String splitChar) throws IOException {
        return Arrays.stream(readFile(fileName).split(splitChar)).filter(s -> !s.equals("")).toList();
    }

    public static void makeDir(String... paths) {
        for (String p : paths) {
            File f = new File(p);
            f.mkdir();
        }
    }

    public static void removeDir(String... paths) throws IOException {
        for (String p : paths) {
            Files.deleteIfExists(Path.of(p));
        }
    }

    public static void removeDir(String path, OS os) {
        try {
            String cmd = null;
            switch (os) {
                case MAC -> {System.out.println("Remove dir mac");}
                case LINUX -> {System.out.println("Remove dir linux");}
                case WINDOWS -> {
                    cmd = String.format("cmd /c rmdir /s /q \"%s\"", path);
                }
            }
            if (cmd != null)
                Runtime.getRuntime().exec(cmd);
        } catch (IOException ignore) {
        }
    }

    public static String joinPath(String parent, String child) {
        return parent + File.separator + child;
    }

    public static Path joinPath(String parent, String child, boolean dummy) {
        return Path.of(joinPath(parent, child));
    }

    public static String joinPaths(String parent, String ...children) {
        String output = parent;

        for (String s : children) {
            output = joinPath(output, s);
        }

        return output;
    }

    public static String getFileName(String name, String path, boolean isFolder) throws IOException {
        List<Path> files = Files.list(Path.of(path)).toList();
        String[] split = name.split("\\.");

        String fn, ext;

        if (isFolder) {
            fn = name;
            ext = "";
        } else {
            ext = "." + split[split.length - 1];
            fn = String.join("", Arrays.copyOfRange(split, 0, split.length - 1));
        }

        int counter = 1;

        while (true) {
            if (!files.contains(joinPath(path, name, true))) {
                return joinPath(path, name);
            }

            name = fn + "(" + counter + ")" + ext;
            counter++;
        }
    }

    public static String getFileName(String name, String path) throws IOException {
        return getFileName(name, path, false);
    }

    public static void write(String path, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(data);
        }
    }

    public static void write(String path, List<String> list) throws IOException {
        write(path, String.join("\n", list));
    }

    public static void append(String path, String data) throws IOException {
        String oldData = null;
        try {
            oldData = readFile(path);
        } catch (IOException ignore) {}

        String output;
        if (oldData == null) {
            output = data;
        } else {
            output = String.format("%s\n%s", oldData, data);
        }

        write(path, output);
    }

    public static void append(String path, List<String> list) throws IOException {
        append(path, String.join("\n", list));
    }

    public static OS getOS() {
        String o = System.getProperty("os.name").strip().toLowerCase();
        if (o.contains("win")) {
            return OS.WINDOWS;
        } else if (o.contains("mac")) {
            return OS.MAC;
        } else if ((o.contains("nix")) || (o.contains("nux")) || (o.contains("aix")) || (o.contains("lin"))) {
            return OS.LINUX;
        } else {
            throw new RuntimeException(String.format("OS not recognized: '%s'!", o));
        }
    }
}
