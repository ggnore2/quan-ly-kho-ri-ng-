import java.util.ArrayList;
import java.util.Arrays;

public class Kho extends ALopDoiTuong implements ILopDoiTuong {
    private String tenKho;
    private String diaDiem;
    private int soLuongTongQuat;
    private int gioiHan;

    @Override
    public void xuatThongTin() {
        System.out.printf("%s,%s,%d,%d\n", this.getTenKho(), this.getDiaDiem(), this.getSoLuongTongQuat(),
                this.getGioiHan());
    }

    public Kho() {

    }

    public Kho(String tenKho, String diaDiem, int soLuongTongQuat, int gioiHan) {
        this.tenKho = tenKho;
        this.diaDiem = diaDiem;
        this.soLuongTongQuat = soLuongTongQuat;
        this.gioiHan = gioiHan;
    }

    // get
    public String getTenKho() {
        return this.tenKho;
    }

    public String getDiaDiem() {
        return this.diaDiem;
    }

    public int getSoLuongTongQuat() {
        return this.soLuongTongQuat;
    }

    public int getGioiHan() {
        return this.gioiHan;
    }

    // set

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setSoLuongTongQuat(int soLuongTongQuat) {
        this.soLuongTongQuat = soLuongTongQuat;
    }

    public void setGioiHan(int gioiHan) {
        this.gioiHan = gioiHan;
    }

    @Override
    public String toString() {
        String result = String.format("%s,%s,%d,%d", this.getTenKho().toLowerCase().trim(),
                this.getDiaDiem().toLowerCase().trim(), this.getSoLuongTongQuat(),
                this.getGioiHan());
        return result;
    }

    public boolean equals(Kho khoNhap) {
        if (!khoNhap.getTenKho().toLowerCase().trim().equals(this.getTenKho().toLowerCase().trim())) {
            return false;
        }
        if (!khoNhap.getDiaDiem().toLowerCase().trim().equals(this.getDiaDiem().toLowerCase().trim())) {
            return false;
        }
        if (!(khoNhap.getSoLuongTongQuat() == this.getSoLuongTongQuat())) {
            return false;
        }
        if (!(khoNhap.getGioiHan() == this.getGioiHan())) {
            return false;
        }
        return true;
    }

    public static Kho fromString(String input) {
        ArrayList<String> listOfThuocTinhs = new ArrayList<String>(Arrays.asList(input.split(",")));
        Kho khoMoi = new Kho();
        khoMoi.setTenKho(listOfThuocTinhs.get(0).toLowerCase().trim());
        khoMoi.setDiaDiem(listOfThuocTinhs.get(1).toLowerCase().trim());
        khoMoi.setSoLuongTongQuat(Integer.valueOf(listOfThuocTinhs.get(2)));
        khoMoi.setGioiHan(Integer.valueOf(listOfThuocTinhs.get(3)));
        return khoMoi;
    }

}
