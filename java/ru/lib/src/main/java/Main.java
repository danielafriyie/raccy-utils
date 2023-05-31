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
        var cprint = new ColorPrint();
        var ss = Arrays.asList("hello", "world", "hi", "anita");
        for (var s : ss) {
            cprint.info(s);
            cprint.warning(s);
            cprint.success(s);
            cprint.error(s);
            cprint.error(new Exception(s));
        }
    }
}