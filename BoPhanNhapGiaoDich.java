import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class BoPhanNhapGiaoDich extends ABoPhanVoiDataBase implements IBoPhanVoiDatabase {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

    public static void taoDatabases() {
        if (!Files.exists(Path.of(ABoPhanVoiDataBase.giaoDichPath))) {
            try {
                String contentToWrite = "ten hang,loai hang,so luong,loai giao dich,gia tong the,thoi diem,ten kho\n";
                Files.writeString(Path.of(ABoPhanVoiDataBase.giaoDichPath), contentToWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // tien hanh nhap giao dich

    public static void tienHanhNhanGiaoDichMua() {
        GiaoDich giaoDichMoi = new GiaoDich();

        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap ten hang");
        String tenHang = sc.nextLine().toLowerCase().trim();
        giaoDichMoi.setTenHang(tenHang);

        System.out.println("Nhap loai hang");
        String loaiHang = sc.nextLine().toLowerCase().trim();
        giaoDichMoi.setLoaiHang(loaiHang);

        System.out.println("Nhap so luong");
        int soLuong = Integer.valueOf(sc.nextLine());
        giaoDichMoi.setSoLuong(soLuong);

        giaoDichMoi.setLoaiGiaoDich("mua");

        System.out.println("Nhap gia tong the");
        double giaTongThe = Double.valueOf(sc.nextLine());
        giaoDichMoi.setGiaTongThe(giaTongThe);

        Date thoiDiem = new Date();
        giaoDichMoi.setThoiDiem(thoiDiem);

        ArrayList<Integer> cacKhoPhuHop = BoPhanNhapXuatKho.timKhoTheoSoLuongDu(soLuong);
        if (!(cacKhoPhuHop.size() >= 1)) {
            System.out.println("Khong co kho de chua");
            sc.close();
            return;
        }
        System.out.println("Chon mot ten kho trong cac ten kho sau");
        BoPhanNhapXuatKho.inKhoTheoIndexes(cacKhoPhuHop);

        System.out.println("nhap ten kho");
        String tenKho = sc.nextLine().toLowerCase().trim();
        giaoDichMoi.setTenKho(tenKho);

        BoPhanNhapGiaoDich.nhapGiaoDich(giaoDichMoi);
        BoPhanNhapXuatKho.tienHanhThucHienGiaoDichMua(giaoDichMoi);
        sc.close();
    }

    public static void tienHanhNhanGiaoDichBan() {
        GiaoDich giaoDichMoi = new GiaoDich();

        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap ten hang");
        String tenHang = sc.nextLine().toLowerCase().trim();
        if (BoPhanNhapXuatKho.timHangTheoTenHang(tenHang).size() < 1) {
            System.out.println("khong co hang");
        }
        giaoDichMoi.setTenHang(tenHang);

        System.out.println("Nhap loai hang");
        String loaiHang = sc.nextLine().toLowerCase().trim();
        giaoDichMoi.setLoaiHang(loaiHang);

        System.out.println("Nhap so luong");
        int soLuong = Integer.valueOf(sc.nextLine());
        giaoDichMoi.setSoLuong(soLuong);

        ArrayList<String> thuocTinhs = BoPhanNhapGiaoDich.taoArrayListString("ten hang", "loai hang");
        ArrayList<String> giaTris = BoPhanNhapGiaoDich.taoArrayListString(giaoDichMoi.getTenHang(),
                String.valueOf(giaoDichMoi.getSoLuong()).toLowerCase().trim());

        int indexKhoVaHangCoSoLuongHangPhuHop = BoPhanNhapXuatKho.timKhoVaHangTheoThuocTinh(thuocTinhs, giaTris);
        if (indexKhoVaHangCoSoLuongHangPhuHop == -1) {
            System.out.println("Khong co du de ban");
            sc.close();
            return;
        }
        String tenKho = " ";
        try {
            String khoVaHang = Files.readString(Path.of(BoPhanNhapXuatKho.khoVaHangPath))
                    .split("\n")[indexKhoVaHangCoSoLuongHangPhuHop];
            tenKho = KhoVaHang.fromString(khoVaHang).getTenKho();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        giaoDichMoi.setLoaiGiaoDich("ban");

        System.out.println("Nhap gia tong the");
        double giaTongThe = Double.valueOf(sc.nextLine());
        giaoDichMoi.setGiaTongThe(giaTongThe);

        Date thoiDiem = new Date();
        giaoDichMoi.setThoiDiem(thoiDiem);

        giaoDichMoi.setTenKho(tenKho);

        BoPhanNhapGiaoDich.nhapGiaoDich(giaoDichMoi);
        BoPhanNhapXuatKho.tienHanhThucHienGiaoDichBan(giaoDichMoi);
        sc.close();

    }

    // nhap giao dich
    public static void nhapGiaoDich(GiaoDich giaoDich) {
        try {
            String contentToWrite = giaoDich.toString() + "\n";
            Files.writeString(Path.of(
                    ABoPhanVoiDataBase.giaoDichPath), contentToWrite,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // thay doi giao dich
    public static void thayDoiGiaoDich(GiaoDich giaoDichCu, GiaoDich giaoDichMoi) {
        try {
            ArrayList<String> listOfGiaoDichs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath)).split("\n")));
            if (listOfGiaoDichs.size() < 2) {
                System.out.println("Khong co giao dich");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfGiaoDichs.size(); i++) {
                GiaoDich giaoDichDoc = GiaoDich.fromString(listOfGiaoDichs.get(i));
                if (giaoDichCu.equals(giaoDichDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfGiaoDichs.set(index, giaoDichMoi.toString());
                String content = String.join("\n", listOfGiaoDichs) + "\n";
                Files.writeString(Path.of(ABoPhanVoiDataBase.giaoDichPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // xoa giao dich
    public static void xoaGiaoDich(GiaoDich giaoDichNhap) {
        try {
            ArrayList<String> listOfGiaoDichs = new ArrayList<String>(
                    Arrays.asList(Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath)).split("\n")));
            if (listOfGiaoDichs.size() < 2) {
                System.out.println("Khong co giao dich");
                return;
            }
            int index = 0;
            for (int i = 1; i < listOfGiaoDichs.size(); i++) {
                GiaoDich giaoDichDoc = GiaoDich.fromString(listOfGiaoDichs.get(i));

                if (giaoDichNhap.equals(giaoDichDoc)) {
                    index = i;
                    break;
                }
            }
            if (index != 0) {
                listOfGiaoDichs.remove(index);
                String content = String.join("\n", listOfGiaoDichs) + "\n";
                Files.writeString(Path.of(ABoPhanVoiDataBase.giaoDichPath), content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // tim tra lai cac vi tri trong database
    public static ArrayList<Integer> timGiaoDichTheoTenHang(String tenHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaTenHang = tenHang.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
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

    public static ArrayList<Integer> timGiaoDichTheoLoaiHang(String loaiHang) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaLoaiHang = loaiHang.toLowerCase().trim();

        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
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

    public static ArrayList<Integer> timGiaoDichTheoSoLuong(int soLuong) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
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

    public static ArrayList<Integer> timGiaoDichTheoLoaiGiaoDich(String loaiGiaoDich) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaLoaiGiaoDich = loaiGiaoDich.toLowerCase().trim();

        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
                String entryLoaiGiaoDich = entry.get(3);
                if (entryLoaiGiaoDich.equals(tieuChuanHoaLoaiGiaoDich)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timGiaoDichTheoGiaTongThe(double giaTongThe) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
                double entryGiaTongThe = Double.valueOf(entry.get(4));
                if (entryGiaTongThe == giaTongThe) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timGiaoDichTheoThoiDiem(Date thoiDiem) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
                Date entryThoiDiem = dateFormat.parse(entry.get(5));
                if (entryThoiDiem.equals(thoiDiem)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timGiaoDichTheoTenKho(String tenKho) {
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        String tieuChuanHoaTenKho = tenKho.toLowerCase().trim();
        try {
            String fileContent = Files.readString(Path.of(ABoPhanVoiDataBase.giaoDichPath));
            ArrayList<String> listOfGiaoDich = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
            for (int i = 1; i < listOfGiaoDich.size(); i++) {
                ArrayList<String> entry = new ArrayList<String>(Arrays.asList(listOfGiaoDich.get(i).split(",")));
                String entryTenKho = entry.get(6);
                if (entryTenKho.equals(tieuChuanHoaTenKho)) {
                    ketQua.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

    public static ArrayList<Integer> timGiaoDichTheoThuocTinh(ArrayList<String> tenCacThuocTinh,
            ArrayList<String> giaTriCacThuocTinh) {
        ArrayList<ArrayList<Integer>> listOfArrayList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ketQua = new ArrayList<Integer>();
        try {
            int index = 0;
            for (String tenThuocTinh : tenCacThuocTinh) {
                String tieuChuanHoaTenThuocTinh = tenThuocTinh.toLowerCase().trim();
                if (tieuChuanHoaTenThuocTinh.equals("ten hang")) {
                    String tenHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoTenHang(tenHang.toLowerCase().trim()));
                }
                if (tieuChuanHoaTenThuocTinh.equals("loai hang")) {
                    String loaiHang = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoLoaiHang(loaiHang.toLowerCase().trim()));

                }
                if (tieuChuanHoaTenThuocTinh.equals("so luong")) {
                    int soLuong = Integer.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoSoLuong(soLuong));

                }
                if (tieuChuanHoaTenThuocTinh.equals("loai giao dich")) {
                    String loaiGiaoDich = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoLoaiGiaoDich(loaiGiaoDich.toLowerCase()
                            .trim()));

                }
                if (tieuChuanHoaTenThuocTinh.equals("gia tong the")) {
                    double giaTongThe = Double.valueOf(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoGiaTongThe(giaTongThe));

                }
                if (tieuChuanHoaTenThuocTinh.equals("thoi diem")) {
                    Date thoiDiem = dateFormat.parse(giaTriCacThuocTinh.get(index));
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoThoiDiem(thoiDiem));
                }
                if (tieuChuanHoaTenThuocTinh.equals("ten kho")) {
                    String tenKho = giaTriCacThuocTinh.get(index);
                    listOfArrayList.add(BoPhanNhapGiaoDich.timGiaoDichTheoTenKho(tenKho.toLowerCase().trim()));
                }
                index += 1;
            }
            if (!(listOfArrayList.size() >= 1)) {
                return ketQua;
            }
            for (int i : listOfArrayList.get(0)) {
                ketQua.add(i);
            }
            for (ArrayList<Integer> arrayList : listOfArrayList) {
                ketQua.retainAll(arrayList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ketQua;
    }

}