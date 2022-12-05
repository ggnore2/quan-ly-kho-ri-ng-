import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GiaoDich {
    private String tenHang;
    private String loaiHang;
    private int soLuong;
    private String loaiGiaoDich;
    private double giaTongThe;
    private Date thoiDiem;
    private String tenKho;

    // constructor
    public GiaoDich() {

    }

    public GiaoDich(String tenHang, String loaiHang, int soLuong, String loaiGiaoDich, double giaTongThe,
            Date thoiDiem, String tenKho) {
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.soLuong = soLuong;
        this.loaiGiaoDich = loaiGiaoDich;
        this.giaTongThe = giaTongThe;
        this.thoiDiem = thoiDiem;
        this.tenKho = tenKho;
    }

    // set
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public void setGiaTongThe(double giaTongThe) {
        this.giaTongThe = giaTongThe;
    }

    public void setThoiDiem(Date thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    // get
    public String getTenHang() {
        return this.tenHang;
    }

    public String getLoaiHang() {
        return this.loaiHang;
    }

    public String getTenKho() {
        return this.tenKho;
    }

    public double getGiaTongThe() {
        return this.giaTongThe;
    }

    public String getLoaiGiaoDich() {
        return this.loaiGiaoDich;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    public Date getThoiDiem() {
        return this.thoiDiem;
    }

    public String toString() {
        String result = String.format("%s,%s,%d,%s,%f,%s,%s", this.getTenHang().toLowerCase().trim(),
                this.getLoaiHang().toLowerCase().trim(),
                this.getSoLuong(), this.getLoaiGiaoDich().toLowerCase().trim(), this.getGiaTongThe(),
                this.getThoiDiem().toString(),
                this.getTenKho().toLowerCase().trim());
        return result;
    }

    public static GiaoDich fromString(String input) {
        ArrayList<String> listOfThuocTinhs = new ArrayList<String>(Arrays.asList(input.split(",")));
        GiaoDich giaoDichMoi = new GiaoDich();
        giaoDichMoi.setTenHang(listOfThuocTinhs.get(0).toLowerCase().trim());
        giaoDichMoi.setLoaiHang(listOfThuocTinhs.get(1).toLowerCase().trim());
        giaoDichMoi.setSoLuong(Integer.valueOf(listOfThuocTinhs.get(2)));
        giaoDichMoi.setLoaiGiaoDich(listOfThuocTinhs.get(3));
        giaoDichMoi.setGiaTongThe(Double.valueOf(listOfThuocTinhs.get(4)));
        try {
            giaoDichMoi.setThoiDiem(BoPhanNhapGiaoDich.dateFormat.parse(listOfThuocTinhs.get(5)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        giaoDichMoi.setTenKho(listOfThuocTinhs.get(6).toLowerCase().trim());
        return giaoDichMoi;
    }

    public boolean equals(GiaoDich giaoDich) {
        if (!(giaoDich.getTenHang().toLowerCase().trim().equals(this.getTenHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(giaoDich.getLoaiHang().toLowerCase().trim().equals(this.getLoaiHang().toLowerCase().trim()))) {
            return false;
        }
        if (!(giaoDich.getSoLuong() == this.getSoLuong())) {
            return false;
        }
        if (!(giaoDich.getLoaiGiaoDich()).toLowerCase().trim().equals(this.getLoaiGiaoDich().toLowerCase().trim())) {
            return false;
        }
        if (!(giaoDich.getGiaTongThe() == this.getGiaTongThe())) {
            return false;
        }
        if (!(giaoDich.getThoiDiem().toString().equals(this.getThoiDiem().toString()))) {
            return false;
        }
        if (!(giaoDich.getTenKho().toLowerCase().trim().equals(this.getTenKho().toLowerCase().trim()))) {
            return false;
        }
        return true;
    }
}
