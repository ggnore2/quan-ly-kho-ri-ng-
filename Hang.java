import java.util.ArrayList;
import java.util.Arrays;

public class Hang extends ALopDoiTuong implements ILopDoiTuong {
    private String tenHang;
    private String loaiHang;
    private int soLuong;
    private String ghiChu = " ";

    @Override
    public void xuatThongTin() {
        System.out.printf("%s,%s,%d,%d\n", this.getTenHang(), this.getLoaiHang(), this.getSoLuong(),
                this.getGhiChu());
    }

    public Hang() {

    }

    public Hang(String tenHang, String loaiHang, int soLuong, String ghiChu) {
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    // get
    public String getTenHang() {
        return this.tenHang;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        String result = String.format("%s,%s,%d,%s", this.getTenHang(), this.getLoaiHang(), this.getSoLuong(),
                this.getGhiChu());
        return result;
    }

    public static Hang fromString(String input) {
        ArrayList<String> listOfThuocTinhs = new ArrayList<String>(Arrays.asList(input.split(",")));
        Hang hangMoi = new Hang();
        hangMoi.setTenHang(listOfThuocTinhs.get(0).toLowerCase().trim());
        hangMoi.setLoaiHang(listOfThuocTinhs.get(1).toLowerCase().trim());
        hangMoi.setSoLuong(Integer.valueOf(listOfThuocTinhs.get(2)));
        hangMoi.setGhiChu(listOfThuocTinhs.get(3));
        return hangMoi;
    }

    public boolean equals(Hang hangNhap) {
        if (!(hangNhap.getTenHang().toLowerCase().trim().equals(this.getTenHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(hangNhap.getLoaiHang().toLowerCase().trim().equals(this.getLoaiHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(hangNhap.getSoLuong() == this.getSoLuong())) {
            return false;
        }
        if (!(hangNhap.getGhiChu().toLowerCase().trim().equals(this.getGhiChu().toLowerCase().trim()))) {
            return false;
        }
        return true;
    }
}
