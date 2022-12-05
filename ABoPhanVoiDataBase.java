import java.util.ArrayList;
import java.util.Arrays;

public class ABoPhanVoiDataBase {
    public static String hangPath = "./hang.txt";
    public static String giaoDichPath = "./giaoDich.txt";
    public static String khoVaHangPath = "./khoVaHang.txt";
    public static String khoPath = "./kho.txt";

    public static ArrayList<String> taoArrayListString(String... inputs) {
        return new ArrayList<String>(Arrays.asList(inputs));
    }
}
