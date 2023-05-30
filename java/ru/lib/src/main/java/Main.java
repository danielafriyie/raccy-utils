import raccy.config.TextConfig;
import raccy.utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = String.format("window.scrollTo(0, %s);",23.5);
        System.out.println(s);
    }
}