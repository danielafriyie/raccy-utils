import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;

import raccy.config.JsonConfig;

public class Main {

    static void go(List<? extends Integer> lst) {
        System.out.println(lst);
    }

    public static void main(String[] args) throws Exception {
        var c = new JsonConfig("C:\\Users\\afriy\\Desktop\\PROJECTS\\raccy-utils\\java\\ru\\bunny\\config.json");
        System.out.println(c.get("firstName"));
        System.out.println(c);
        c.put("testy", "kyle");
        c.save();
    }
}