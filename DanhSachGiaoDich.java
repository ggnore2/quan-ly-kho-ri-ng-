public class DanhSachGiaoDich {
    public GiaoDich[] danhSach;
    public int soLuong = 0;

    public DanhSachGiaoDich(GiaoDich... danhSachNhap) {
        this.soLuong = danhSachNhap.length;
        danhSach = new GiaoDich[this.soLuong];
        int i = 0;
        for (GiaoDich gD : danhSachNhap) {
            danhSach[i] = gD;
        }
    }

    public void Nhap(GiaoDich giaoDich) {
        this.soLuong += 1;
        GiaoDich[] danhSachMoi = new GiaoDich[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            if (i == this.soLuong - 1) {
                danhSachMoi[i] = giaoDich;
            } else {
                danhSachMoi[i] = this.danhSach[i];
            }
        }
        this.danhSach = danhSachMoi;
    }

    public GiaoDich get(int i) {
        return this.danhSach[i];
    }

    public void set(int i, GiaoDich giaoDich) {
        this.danhSach[i] = giaoDich;
    }
}
