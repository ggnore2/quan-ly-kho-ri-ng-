import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainClass {
    public static ArrayList<String> taoArrayListString(String... inputs) {
        return new ArrayList<String>(Arrays.asList(inputs));
    }

    public static void main(String[] args) {
        Date d = new Date();
        GiaoDich giaoDich = new GiaoDich("banh kem", "thuc an", 5, "mua", 5.5, d, "kho a");
        BoPhanNhapGiaoDich.nhapGiaoDich(giaoDich);
        ArrayList<String> thuocTinhs = taoArrayListString("ten kho");
        ArrayList<String> giaTris = taoArrayListString("kho a");
        BoPhanBaoCao.showGiaoDichTheoThuocTinh(thuocTinhs, giaTris);
    }
}
