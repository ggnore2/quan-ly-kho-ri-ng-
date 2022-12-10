public class DanhSachKhoVaHang {
    public KhoVaHang[] danhSach;
    public int soLuong = 0;

    public DanhSachKhoVaHang(KhoVaHang... danhSachNhap) {
        this.soLuong = danhSachNhap.length;
        danhSach = new KhoVaHang[this.soLuong];
        int i = 0;
        for (KhoVaHang kVH : danhSachNhap) {
            danhSach[i] = kVH;
        }
    }

    public void Nhap(KhoVaHang kVH) {
        this.soLuong += 1;
        KhoVaHang[] danhSachMoi = new KhoVaHang[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            if (i == this.soLuong - 1) {
                danhSachMoi[i] = kVH;
            } else {
                danhSachMoi[i] = this.danhSach[i];
            }
        }
        this.danhSach = danhSachMoi;
    }

    public KhoVaHang get(int i) {
        return this.danhSach[i];
    }

    public void set(int i, KhoVaHang kVH) {
        this.danhSach[i] = kVH;
    }
}
