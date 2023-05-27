import raccy.config.TextConfig;
import raccy.utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        var c = new TextConfig("C:\\Users\\afriy\\Desktop\\PROJECTS\\raccy-utils\\java\\bunny\\config.txt");
        System.out.println(c);
        Utils.removeDir("C:\\Users\\afriy\\Desktop\\PROJECTS\\raccy-utils\\java\\bunny\\hello");
    }
}