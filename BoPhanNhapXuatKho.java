import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class BoPhanNhapXuatKho extends ABoPhanVoiDataBase implements IBoPhanVoiDatabase {
    public BoPhanNhapXuatKho() {

    }

    public static void taoDatabases() {

        // tao hang.txt
        if (!Files.exists(Path.of(BoPhanNhapXuatKho.hangPath))) {
            try {
                String contentToWrite = "ten hang,loai hang,so luong,ghi chu\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.hangPath), contentToWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // tao kho.txt
        if (!Files.exists(Path.of(BoPhanNhapXuatKho.khoPath))) {
            try {
                String contentToWrite = "ten kho,dia diem,so luong tong quat,gioi han\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoPath), contentToWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // tao khoVaHang.txt
        if (!Files.exists(Path.of(BoPhanNhapXuatKho.khoVaHangPath))) {
            try {
                String contentToWrite = "ten kho,dia diem,ten hang,loai hang,so luong\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoVaHangPath), contentToWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tienHanhThucHienGiaoDichMua(GiaoDich giaoDichNhap) {
        // update Hang
        ArrayList<String> thuocTinhHangs = BoPhanNhapXuatKho.taoArrayListString("ten hang", "loai hang");
        ArrayList<String> giaTriHangs = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenHang(),
                giaoDichNhap.getLoaiHang());
        int indexHang = BoPhanNhapXuatKho.timHangTheoThuocTinh(thuocTinhHangs, giaTriHangs);
        Hang hang = new Hang();

        if (indexHang < 0) {
            hang.setTenHang(giaoDichNhap.getTenHang());
            hang.setLoaiHang(giaoDichNhap.getLoaiHang());
            hang.setSoLuong(giaoDichNhap.getSoLuong());
            BoPhanNhapXuatKho.nhapHang(hang);
        } else {
            try {
                String hangString = Files.readString(Path.of(BoPhanNhapXuatKho.hangPath)).split("\n")[indexHang];
                hang = Hang.fromString(hangString);
                Hang hangMoi = new Hang();
                hangMoi.setTenHang(hang.getTenHang());
                hangMoi.setLoaiHang(hang.getLoaiHang());
                hangMoi.setSoLuong(hang.getSoLuong() + giaoDichNhap.getSoLuong());
                BoPhanNhapXuatKho.thayDoiHang(hang, hangMoi);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // update kho

        ArrayList<String> thuocTinhKhos = BoPhanNhapXuatKho.taoArrayListString("ten kho");
        ArrayList<String> giaTriKhos = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenKho());
        int indexKho = BoPhanNhapXuatKho.timKhoTheoThuocTinh(thuocTinhKhos, giaTriKhos);
        Kho kho = new Kho();
        String diaDiem = "";
        try {
            String khoString = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath)).split("\n")[indexKho];
            kho = Kho.fromString(khoString);
            Kho khoMoi = new Kho();
            diaDiem = kho.getDiaDiem();
            khoMoi.setTenKho(kho.getTenKho());
            khoMoi.setDiaDiem(kho.getDiaDiem());
            khoMoi.setSoLuongTongQuat(kho.getSoLuongTongQuat() + giaoDichNhap.getSoLuong());
            khoMoi.setGioiHan(kho.getGioiHan());
            BoPhanNhapXuatKho.thayDoiKho(kho, khoMoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // update kho va hang
        ArrayList<String> thuocTinhKhoVaHangs = BoPhanNhapXuatKho.taoArrayListString("ten kho", "ten hang",
                "loai hang");
        ArrayList<String> giaTriKhoVaHangs = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenKho(),
                giaoDichNhap.getTenHang(), giaoDichNhap.getLoaiHang());
        int indexKhoVaHang = BoPhanNhapXuatKho.timKhoVaHangTheoThuocTinh(thuocTinhKhoVaHangs, giaTriKhoVaHangs);
        KhoVaHang khoVaHang = new KhoVaHang();

        if (indexKhoVaHang == -1) {
            KhoVaHang khoVaHangMoi = new KhoVaHang();
            khoVaHangMoi.setTenKho(giaoDichNhap.getTenKho());
            khoVaHangMoi.setDiaDiem(diaDiem);
            khoVaHangMoi.setTenHang(giaoDichNhap.getTenHang());
            khoVaHangMoi.setLoaiHang(giaoDichNhap.getLoaiHang());
            khoVaHangMoi.setSoLuong(giaoDichNhap.getSoLuong());
            BoPhanNhapXuatKho.nhapKhoVaHang(khoVaHangMoi);
        } else {
            try {
                String khoVaHangString = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath))
                        .split("\n")[indexKhoVaHang];
                khoVaHang = KhoVaHang.fromString(khoVaHangString);
                KhoVaHang khoVaHangMoi = new KhoVaHang();
                khoVaHangMoi.setTenKho(khoVaHang.getTenKho());
                khoVaHangMoi.setDiaDiem(khoVaHang.getDiaDiem());
                khoVaHangMoi.setTenHang(khoVaHang.getTenHang());
                khoVaHangMoi.setLoaiHang((khoVaHang.getLoaiHang()));
                khoVaHangMoi.setSoLuong(khoVaHang.getSoLuong() + giaoDichNhap.getSoLuong());
                BoPhanNhapXuatKho.thayDoiKhoVaHang(khoVaHang, khoVaHangMoi);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tienHanhThucHienGiaoDichBan(GiaoDich giaoDichNhap) {
        // update Hang
        ArrayList<String> thuocTinhHangs = BoPhanNhapXuatKho.taoArrayListString("ten hang", "loai hang");
        ArrayList<String> giaTriHangs = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenHang(),
                giaoDichNhap.getLoaiHang());
        int indexHang = BoPhanNhapXuatKho.timHangTheoThuocTinh(thuocTinhHangs, giaTriHangs);
        Hang hang = new Hang();

        try {
            String hangString = Files.readString(Path.of(BoPhanNhapXuatKho.hangPath)).split("\n")[indexHang];
            hang = Hang.fromString(hangString);
            Hang hangMoi = new Hang();
            hangMoi.setTenHang(hang.getTenHang());
            hangMoi.setLoaiHang(hang.getLoaiHang());
            hangMoi.setSoLuong(hang.getSoLuong() - giaoDichNhap.getSoLuong());
            BoPhanNhapXuatKho.thayDoiHang(hang, hangMoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // update kho

        ArrayList<String> thuocTinhKhos = BoPhanNhapXuatKho.taoArrayListString("ten kho");
        ArrayList<String> giaTriKhos = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenKho());
        int indexKho = BoPhanNhapXuatKho.timKhoTheoThuocTinh(thuocTinhKhos, giaTriKhos);
        Kho kho = new Kho();
        try {
            String khoString = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath)).split("\n")[indexKho];
            kho = Kho.fromString(khoString);
            Kho khoMoi = new Kho();
            khoMoi.setTenKho(kho.getTenKho());
            khoMoi.setDiaDiem(kho.getDiaDiem());
            khoMoi.setSoLuongTongQuat(kho.getSoLuongTongQuat() - giaoDichNhap.getSoLuong());
            khoMoi.setGioiHan(kho.getSoLuongTongQuat());
            BoPhanNhapXuatKho.thayDoiKho(kho, khoMoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // update kho va hang
        ArrayList<String> thuocTinhKhoVaHangs = BoPhanNhapXuatKho.taoArrayListString("ten kho", "ten hang",
                "loai hang");
        ArrayList<String> giaTriKhoVaHangs = BoPhanNhapXuatKho.taoArrayListString(giaoDichNhap.getTenKho(),
                giaoDichNhap.getTenHang(), giaoDichNhap.getLoaiHang());
        int indexKhoVaHang = BoPhanNhapXuatKho.timKhoVaHangTheoThuocTinh(thuocTinhKhoVaHangs, giaTriKhoVaHangs);
        KhoVaHang khoVaHang = new KhoVaHang();

        try {
            String khoVaHangString = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath))
                    .split("\n")[indexKhoVaHang];
            khoVaHang = KhoVaHang.fromString(khoVaHangString);
            KhoVaHang khoVaHangMoi = new KhoVaHang();
            khoVaHangMoi.setTenKho(khoVaHang.getTenKho());
            khoVaHangMoi.setDiaDiem(khoVaHang.getDiaDiem());
            khoVaHangMoi.setTenHang(khoVaHang.getTenHang());
            khoVaHangMoi.setLoaiHang((khoVaHang.getLoaiHang()));
            khoVaHangMoi.setSoLuong(khoVaHang.getSoLuong() - giaoDichNhap.getSoLuong());
            BoPhanNhapXuatKho.thayDoiKhoVaHang(khoVaHang, khoVaHangMoi);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // nhap hang
    public static void nhapHang(Hang hangNhap) {
        try {
            Files.writeString(Path.of(BoPhanNhapXuatKho.hangPath), hangNhap.toString() + "\n",
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void nhapCacHang(DanhSachHang danhSachHang) {
        for (Hang hang : danhSachHang.danhSach) {
            BoPhanNhapXuatKho.nhapHang(hang);
        }
    }

    // thay doi hang
    public static void thayDoiHang(Hang hangCu, Hang hangMoi) {
        try {
            ArrayList<String> listOfHangs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.hangPath)).split("\n")));
            if (listOfHangs.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfHangs.size(); i++) {
                Hang hangDoc = Hang.fromString(listOfHangs.get(i));
                if (hangCu.equals(hangDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfHangs.set(index, hangMoi.toString());
                String content = String.join("\n", listOfHangs) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.hangPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // xoa hang
    public static void xoaHang(Hang hangNhap) {
        try {
            ArrayList<String> listOfHangs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.hangPath)).split("\n")));
            if (listOfHangs.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfHangs.size(); i++) {
                Hang hangDoc = Hang.fromString(listOfHangs.get(i));

                if (hangNhap.equals(hangDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfHangs.remove(index);
                String content = String.join("\n", listOfHangs) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.hangPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // nhap kho
    public static void nhapKho(Kho khoNhap) {
        try {
            Files.writeString(Path.of(
                    BoPhanNhapXuatKho.khoPath), khoNhap.toString() + "\n",
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void nhapCacKho(DanhSachKho danhSachKho) {
        for (Kho kho : danhSachKho.danhSach) {
            BoPhanNhapXuatKho.nhapKho(kho);
        }
    }

    // thay doi kho
    public static void thayDoiKho(Kho khoCu, Kho khoMoi) {
        try {
            ArrayList<String> listOfKhos = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.khoPath)).split("\n")));
            if (listOfKhos.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfKhos.size(); i++) {
                Kho khoDoc = Kho.fromString(listOfKhos.get(i));
                if (khoCu.equals(khoDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfKhos.set(index, khoMoi.toString());
                String content = String.join("\n", listOfKhos) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // xoa kho
    public static void xoaKho(Kho khoNhap) {
        try {
            ArrayList<String> listOfKhos = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.khoPath)).split("\n")));
            if (listOfKhos.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfKhos.size(); i++) {
                Kho khoDoc = Kho.fromString(listOfKhos.get(i));

                if (khoNhap.equals(khoDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfKhos.remove(index);
                String content = String.join("\n", listOfKhos) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // nhap kho va hang
    public static void nhapKhoVaHang(KhoVaHang khoVaHangNhap) {
        try {
            Files.writeString(Path.of(
                    BoPhanNhapXuatKho.khoVaHangPath), khoVaHangNhap.toString() + "\n",
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void nhapCacKhoVaHang(DanhSachKhoVaHang danhSachKhoVaHang) {
        for (KhoVaHang khoVaHang : danhSachKhoVaHang.danhSach) {
            BoPhanNhapXuatKho.nhapKhoVaHang(khoVaHang);
        }
    }

    // thay doi kho va hang
    public static void thayDoiKhoVaHang(KhoVaHang khoVaHangCu, KhoVaHang KhoVaHangMoi) {
        try {
            ArrayList<String> listOfKhoVaHangs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath)).split("\n")));
            if (listOfKhoVaHangs.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfKhoVaHangs.size(); i++) {
                KhoVaHang khoVaHangDoc = KhoVaHang.fromString(listOfKhoVaHangs.get(i));
                if (khoVaHangCu.equals(khoVaHangDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfKhoVaHangs.set(index, KhoVaHangMoi.toString());
                String content = String.join("\n", listOfKhoVaHangs) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoVaHangPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // xoa kho va hang
    public static void xoaKhoVaHang(KhoVaHang khoVaHangNhap) {
        try {
            ArrayList<String> listOfKhoVaHangs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath)).split("\n")));
            if (listOfKhoVaHangs.size() < 2) {
                System.out.println("Khong co hang");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfKhoVaHangs.size(); i++) {
                KhoVaHang khoVaHangDoc = KhoVaHang.fromString(listOfKhoVaHangs.get(i));

                if (khoVaHangNhap.equals(khoVaHangDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfKhoVaHangs.remove(index);
                String content = String.join("\n", listOfKhoVaHangs) + "\n";
                Files.writeString(Path.of(BoPhanNhapXuatKho.khoVaHangPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // in ra
    public static void inHangTheoIndexes(ArrayList<Integer> indexes) {
        String result = "";
        try {
            ArrayList<String> listOfHang = new ArrayList<String>(Arrays.asList(
                    Files.readString(Path.of(BoPhanNhapXuatKho.hangPath)).split("\n")));
            if (listOfHang.size() == 0) {
                System.out.println("khong co hang");
            }
            for (int index : indexes) {
                result += listOfHang.get(index) + "\n";
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inKhoTheoIndexes(ArrayList<Integer> indexes) {
        String result = "";
        try {
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(
                    Files.readString(Path.of(BoPhanNhapXuatKho.khoPath)).split("\n")));
            if (listOfKho.size() == 0) {
                System.out.println("khong co kho");
            }
            for (int index : indexes) {
                result += listOfKho.get(index) + "\n";
            }
            System.out.println(result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inKhoVaHangTheoIndexes(ArrayList<Integer> indexes) {
        String result = "";
        try {
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(
                    Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath)).split("\n")));
            if (listOfKhoVaHang.size() == 0) {
                System.out.println("khong co kho va hang");
            }
            for (int index : indexes) {
                result += listOfKhoVaHang.get(index) + "\n";
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // tim kho
    public static ArrayList<Integer> timKhoTheoTenKho(String tenKho) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaTenKho = tenKho.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath));
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKho.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKho.get(i).split(",")));
                String entryTenKho = entry.get(0);
                if (entryTenKho.equals(tieuChuanHoaTenKho)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoTheoDiaDiem(String diaDiem) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaDiaDiem = diaDiem.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath));
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKho.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKho.get(i).split(",")));
                String entryDiaDiem = entry.get(1);
                if (entryDiaDiem.equals(tieuChuanHoaDiaDiem)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoTheoSoLuongTongQuat(int soLuongTongQuat) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath));
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKho.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKho.get(i).split(",")));
                int entrySoLuongTongQuat = Integer.valueOf(entry.get(2));
                if (entrySoLuongTongQuat == soLuongTongQuat) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoTheoGioiHan(int gioiHan) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath));
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKho.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKho.get(i).split(",")));
                int entryGioiHan = Integer.valueOf(entry.get(3));
                if (entryGioiHan == gioiHan) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoTheoSoLuongDu(int soLuongDu) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoPath));
            ArrayList<String> listOfKho = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKho.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKho.get(i).split(",")));
                int entrySoLuongDu = Integer.valueOf(entry.get(3)) - Integer.valueOf(2);
                if (entrySoLuongDu >= soLuongDu) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static int timKhoTheoThuocTinh(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        int index = 0;
        for (String tenThuocTinh : tenCacThuocTinh) {
            String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
            if (tieuChuanHoaTenThuocTinh.equals("ten kho")) {
                String tenKho = giaTriCacThuocTinh.get(index);
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoTenKho(tenKho.toLowerCase().trim()));
            }
            if (tieuChuanHoaTenThuocTinh.equals("dia diem")) {
                String diaDiem = giaTriCacThuocTinh.get(index);
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoDiaDiem(diaDiem.toLowerCase().trim()));
            }
            if (tieuChuanHoaTenThuocTinh.equals("so luong tong quat")) {
                int soLuongTongQuat = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoSoLuongTongQuat(soLuongTongQuat));
            }
            if (tieuChuanHoaTenThuocTinh.equals("gioi han")) {
                int gioiHan = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoGioiHan(gioiHan));
            }
            if (tieuChuanHoaTenThuocTinh.equals("so luong du kho")) {
                int soLuongDu = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoSoLuongDu(soLuongDu));
            }
            index += 1;
        }
        if (!(listOfArrayList.size() >= 1)) {
            return -1;
        }
        for (int i : listOfArrayList.get(0)) {
            ketQua.add(i);
        }
        for (ArrayList<Integer> entry : listOfArrayList) {
            ketQua.retainAll(entry);
        }
        if (ketQua.size() < 1) {
            return -1;
        }
        return ketQua.get(0);
    }

    public static ArrayList<Integer> timKhoTheoThuocTinhReturnNhieuIndexes(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        int index = 0;
        for (String tenThuocTinh : tenCacThuocTinh) {
            String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
            if (tieuChuanHoaTenThuocTinh.equals("ten kho")) {
                String tenKho = giaTriCacThuocTinh.get(index);
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoTenKho(tenKho.toLowerCase().trim()));
            }
            if (tieuChuanHoaTenThuocTinh.equals("dia diem")) {
                String diaDiem = giaTriCacThuocTinh.get(index);
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoDiaDiem(diaDiem.toLowerCase().trim()));
            }
            if (tieuChuanHoaTenThuocTinh.equals("so luong tong quat")) {
                int soLuongTongQuat = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoSoLuongTongQuat(soLuongTongQuat));
            }
            if (tieuChuanHoaTenThuocTinh.equals("gioi han")) {
                int gioiHan = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoGioiHan(gioiHan));
            }
            if (tieuChuanHoaTenThuocTinh.equals("so luong du kho")) {
                int soLuongDu = Integer.valueOf(giaTriCacThuocTinh.get(index));
                listOfArrayList.add(BoPhanNhapXuatKho.timKhoTheoSoLuongDu(soLuongDu));
            }
            index += 1;
        }
        if (!(listOfArrayList.size() >= 1)) {
            return ketQua;
        }
        for (int i : listOfArrayList.get(0)) {
            ketQua.add(i);
        }
        for (ArrayList<Integer> entry : listOfArrayList) {
            ketQua.retainAll(entry);
        }
        if (ketQua.size() < 1) {
            return ketQua;
        }
        return ketQua;
    }

    // tim hang
    public static ArrayList<Integer> timHangTheoTenHang(String tenHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaTenHang = tenHang.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.hangPath));
            ArrayList<String> listOfHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfHang.get(i).split(",")));
                String entryTenHang = entry.get(0);
                if (entryTenHang.equals(tieuChuanHoaTenHang)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timHangTheoLoaiHang(String loaiHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaLoaiHang = loaiHang.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.hangPath));
            ArrayList<String> listOfHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfHang.get(i).split(",")));
                String entryLoaiHang = entry.get(1);
                if (entryLoaiHang.equals(tieuChuanHoaLoaiHang)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timHangTheoSoLuong(int soLuong) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.hangPath));
            ArrayList<String> listOfHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfHang.get(i).split(",")));
                int entrySoLuong = Integer.valueOf(entry.get(2));
                if (entrySoLuong == soLuong) {
                    ketQua.add(i);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static int timHangTheoThuocTinh(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        try {
            int index = 0;
            for (String tenThuocTinh : tenCacThuocTinh) {
                String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
                if (tieuChuanHoaTenThuocTinh.equals("ten hang")) {
                    String tenHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoTenHang(tenHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("loai hang")) {
                    String loaiHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoLoaiHang(loaiHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("so luong")) {
                    int soLuong = Integer.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoSoLuong(soLuong));
                }
                index += 1;
            }
            if (!(listOfArrayList.size() >= 1)) {
                return -1;
            }
            for (int i : listOfArrayList.get(0)) {
                ketQua.add(i);
            }
            for (ArrayList<Integer> entry : listOfArrayList) {
                ketQua.retainAll(entry);
            }
            if (ketQua.size() < 1) {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua.get(0);
    }

    public static ArrayList<Integer> timHangTheoThuocTinhReturnNhieuIndexes(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        try {
            int index = 0;
            for (String tenThuocTinh : tenCacThuocTinh) {
                String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
                if (tieuChuanHoaTenThuocTinh.equals("ten hang")) {
                    String tenHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoTenHang(tenHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("loai hang")) {
                    String loaiHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoLoaiHang(loaiHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("so luong")) {
                    int soLuong = Integer.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapXuatKho.timHangTheoSoLuong(soLuong));
                }
                index += 1;
            }
            if (!(listOfArrayList.size() >= 1)) {
                return ketQua;
            }
            for (int i : listOfArrayList.get(0)) {
                ketQua.add(i);
            }
            for (ArrayList<Integer> entry : listOfArrayList) {
                ketQua.retainAll(entry);
            }
            if (ketQua.size() < 1) {
                return ketQua;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    // tim kho va hang
    public static ArrayList<Integer> timKhoVaHangTheoTenKho(String tenKho) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaKho = tenKho.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath));
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKhoVaHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKhoVaHang.get(i).split(",")));
                String entryTenKho = entry.get(0);
                if (entryTenKho.equals(tieuChuanHoaKho)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoVaHangTheoDiaDiem(String diaDiem) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaDiaDiem = diaDiem.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath));
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKhoVaHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKhoVaHang.get(i).split(",")));
                String entryDiaDiem = entry.get(1);
                if (entryDiaDiem.equals(tieuChuanHoaDiaDiem)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoVaHangTheoTenHang(String tenHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaTenHang = tenHang.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath));
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKhoVaHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKhoVaHang.get(i).split(",")));
                String entryTenHang = entry.get(2);
                if (entryTenHang.equals(tieuChuanHoaTenHang)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoVaHangTheoLoaiHang(String loaiHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaLoaiHang = loaiHang.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath));
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKhoVaHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKhoVaHang.get(i).split(",")));
                String entryLoaiHang = entry.get(3);
                if (entryLoaiHang.equals(tieuChuanHoaLoaiHang)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timKhoVaHangTheoSoLuong(int soLuong) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath));
            ArrayList<String> listOfKhoVaHang = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfKhoVaHang.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfKhoVaHang.get(i).split(",")));
                int entrySoLuong = Integer.valueOf(entry.get(4));
                if (entrySoLuong == soLuong) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static int timKhoVaHangTheoThuocTinh(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        try {
            int index = 0;
            for (String tenThuocTinh : tenCacThuocTinh) {
                String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
                if (tieuChuanHoaTenThuocTinh.equals("ten kho")) {
                    String tenKho = giaTriCacThuocTinh.get(index).toLowerCase().trim();
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoTenKho(tenKho));
                }
                if (tieuChuanHoaTenThuocTinh.equals("dia diem")) {
                    String diaDiem = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoDiaDiem(diaDiem.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("ten hang")) {
                    String tenHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoTenHang(tenHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("loai hang")) {
                    String loaiHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoLoaiHang(loaiHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("so luong")) {
                    int soLuong = Integer.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoSoLuong(soLuong));
                }
                index += 1;
            }
            if (!(listOfArrayList.size() >= 1)) {
                return -1;
            }
            for (int i : listOfArrayList.get(0)) {
                ketQua.add(i);
            }
            for (ArrayList<Integer> entry : listOfArrayList) {
                ketQua.retainAll(entry);
            }
            if (ketQua.size() < 1) {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua.get(0);
    }

    public static ArrayList<Integer> timKhoVaHangTheoThuocTinhReturnNhieuIndexes(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        try {
            int index = 0;
            for (String tenThuocTinh : tenCacThuocTinh) {
                String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
                if (tieuChuanHoaTenThuocTinh.equals("ten kho")) {
                    String tenKho = giaTriCacThuocTinh.get(index).toLowerCase().trim();
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoTenKho(tenKho));
                }
                if (tieuChuanHoaTenThuocTinh.equals("dia diem")) {
                    String diaDiem = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoDiaDiem(diaDiem.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("ten hang")) {
                    String tenHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoTenHang(tenHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("loai hang")) {
                    String loaiHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoLoaiHang(loaiHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("so luong")) {
                    int soLuong = Integer.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapXuatKho.timKhoVaHangTheoSoLuong(soLuong));
                }
                index += 1;
            }
            if (!(listOfArrayList.size() >= 1)) {
                return ketQua;
            }
            for (int i : listOfArrayList.get(0)) {
                ketQua.add(i);
            }
            for (ArrayList<Integer> entry : listOfArrayList) {
                ketQua.retainAll(entry);
            }
            if (ketQua.size() < 1) {
                return ketQua;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }
}
