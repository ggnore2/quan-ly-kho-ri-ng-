import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class BoPhanBaoCao {

    public BoPhanBaoCao() {

    }

    public static void showGiaoDich() {
        try {
            System.out.println(Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showGiaoDichTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        try {
            ArrayList<Integer> listOfIndexes = BoPhanNhapGiaoDich.timGiaoDichTheoThuocTinhReturnNhieuIndexes(thuocTinhs,
                    giaTris);
            ArrayList<String> content = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath)).split("\n")));
            if (listOfIndexes.size() < 1) {
                System.out.println("Khong co");
                return;
            }
            for (int i : listOfIndexes) {
                System.out.println(content.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showHang() {
        try {
            System.out.println(Files.readString(Path.of(ABoPhanVoiDataBase.hangPath)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showHangTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        try {
            ArrayList<Integer> listOfIndexes = BoPhanNhapXuatKho.timHangTheoThuocTinhReturnNhieuIndexes(thuocTinhs,
                    giaTris);
            ArrayList<String> content = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.hangPath)).split("\n")));
            if (listOfIndexes.size() < 1) {
                System.out.println("Khong co");
                return;
            }
            for (int i : listOfIndexes) {
                System.out.println(content.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showKho() {
        try {
            System.out.println(Files.readString(Path.of(ABoPhanVoiDataBase.khoPath)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showKhoTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        try {
            ArrayList<Integer> listOfIndexes = BoPhanNhapXuatKho.timKhoTheoThuocTinhReturnNhieuIndexes(thuocTinhs,
                    giaTris);
            ArrayList<String> content = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.khoPath)).split("\n")));
            if (listOfIndexes.size() < 1) {
                System.out.println("Khong co");
                return;
            }
            for (int i : listOfIndexes) {
                System.out.println(content.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showKhoVaHang() {
        try {
            System.out.println(Files.readString(Path.of(ABoPhanVoiDataBase.khoVaHangPath)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showKhoVaHangTheoThuocTinh(ArrayList<String> thuocTinhs, ArrayList<String> giaTris) {
        try {
            ArrayList<Integer> listOfIndexes = BoPhanNhapXuatKho.timKhoVaHangTheoThuocTinhReturnNhieuIndexes(thuocTinhs,
                    giaTris);
            ArrayList<String> content = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.khoVaHangPath)).split("\n")));
            if (listOfIndexes.size() < 1) {
                System.out.println("Khong co");
                return;
            }
            for (int i : listOfIndexes) {
                System.out.println(content.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
