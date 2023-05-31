import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;

import raccy.logger.colorprint.ColorPrint;

public class Main {

    static void go(List<? extends Integer> lst) {
        System.out.println(lst);
    }

    public static void main(String[] args) throws Exception {
        var ss = Arrays.asList("hello", "world", "hi", "anita");
        for (var s : ss) {
            ColorPrint.info(s);
            ColorPrint.warning(s);
            ColorPrint.success(s);
            ColorPrint.error(s);
            ColorPrint.error(new Exception(s));
        }
    }
}