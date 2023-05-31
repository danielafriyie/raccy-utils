import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;

import raccy.logger.colorprint.ColorPrint;
import raccy.logger.simple.LazyLogger;
import raccy.logger.simple.PersistentLogger;
import raccy.utils.Utils;

public class Main {

    static void go(List<? extends Integer> lst) {
        System.out.println(lst);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Path: " + Paths.get("").toAbsolutePath());
        var cprint = new PersistentLogger();
        var ss = Arrays.asList("hello", "world", "hi", "anita");
        for (var s : ss) {
            cprint.info(s);
            cprint.warning(s);
            cprint.success(s);
            cprint.error(s);
//            cprint.error(new Exception(s));
        }
        cprint.close();
    }
}