import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static void go(List<? extends Integer> lst) {
        System.out.println(lst);
    }

    public static void main(String[] args) throws Exception {
        go(new ArrayList<>());
        go(Arrays.asList(Integer.valueOf(23), Integer.valueOf(23), Integer.valueOf(2)));
    }
}