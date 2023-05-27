package raccy.utils;

import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
}
