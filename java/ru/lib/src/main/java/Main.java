import raccy.config.TextConfig;
import raccy.utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        String fn = Utils.getFileName("config.txt", "C:\\Users\\afriy\\Desktop\\PROJECTS\\raccy-utils\\java\\ru\\bunny");
        System.out.println(fn);
    }
}