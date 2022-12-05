import java.util.ArrayList;
import java.util.Arrays;

public class KhoVaHang implements IDonVi {
    private String tenKho;
    private String diaDiem;
    private String tenHang;
    private String loaiHang;
    private int soLuong;

    public KhoVaHang() {
    }

    public KhoVaHang(String tenKho, String diaDiem, String tenHang, String loaiHang, int soLuong) {
        this.tenKho = tenKho;
        this.diaDiem = diaDiem;
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.soLuong = soLuong;
    }

    // get
    public String getTenKho() {
        return this.tenKho;
    }

    public String getDiaDiem() {
        return this.diaDiem;
    }

    public String getTenHang() {
        return this.tenHang;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    // set
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        String result = String.format("%s,%s,%s,%s,%d", this.getTenKho().toLowerCase().trim(), this.getDiaDiem()
                .toLowerCase().trim(), this.getTenHang().toLowerCase().trim(), this.getLoaiHang().toLowerCase().trim(),
                this.getSoLuong());
        return result;
    }

    public static KhoVaHang fromString(String input) {
        ArrayList<String> listOfThuocTinhs = new ArrayList<String>(Arrays.asList(input.split(",")));
        KhoVaHang khoVaHangMoi = new KhoVaHang();
        khoVaHangMoi.setTenKho(listOfThuocTinhs.get(0).toLowerCase().trim());
        khoVaHangMoi.setDiaDiem(listOfThuocTinhs.get(1).toLowerCase().trim());
        khoVaHangMoi.setTenHang(listOfThuocTinhs.get(2).toLowerCase().trim());
        khoVaHangMoi.setLoaiHang(listOfThuocTinhs.get(3).toLowerCase().trim());
        khoVaHangMoi.setSoLuong(Integer.valueOf(listOfThuocTinhs.get(4)));
        return khoVaHangMoi;
    }

    public boolean equals(KhoVaHang khoVaHangNhap) {
        if (!(khoVaHangNhap.getTenKho().toLowerCase().trim().equals(this.getTenKho().toLowerCase().trim()))) {
            return false;
        }
        if (!(khoVaHangNhap.getDiaDiem().toLowerCase().trim().equals(this.getDiaDiem().toLowerCase().trim()))) {
            return false;
        }
        if (!(khoVaHangNhap.getTenHang().toLowerCase().trim().equals(this.getTenHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(khoVaHangNhap.getLoaiHang().toLowerCase().trim().equals(this.getLoaiHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(khoVaHangNhap.getSoLuong() == this.getSoLuong())) {
            return false;
        }

        return true;
    }
}
