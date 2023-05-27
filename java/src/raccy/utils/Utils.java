package raccy.utils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public class Utils {

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

    public static void makeDir(String ...paths) {
        for (String p : paths) {
            File f = new File(p);
            f.mkdir();
        }
    }

    public static void removeDir(String ...paths) throws IOException {
        for (String p : paths) {
            Files.deleteIfExists(Path.of(p));
        }
    }

    public static String getFileName(String name, String path, boolean isFolder) {
        return null;
    }
}
