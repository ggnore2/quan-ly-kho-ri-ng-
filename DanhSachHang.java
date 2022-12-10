public class DanhSachHang {
    public Hang[] danhSach;
    public int soLuong = 0;

    public DanhSachHang(Hang... danhSachNhap) {
        this.soLuong = danhSachNhap.length;
        danhSach = new Hang[this.soLuong];
        int i = 0;
        for (Hang h : danhSachNhap) {
            danhSach[i] = h;
        }
    }

    public void Nhap(Hang h) {
        this.soLuong += 1;
        Hang[] danhSachMoi = new Hang[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            if (i == this.soLuong - 1) {
                danhSachMoi[i] = h;
            } else {
                danhSachMoi[i] = this.danhSach[i];
            }
        }
        this.danhSach = danhSachMoi;
    }

    public Hang get(int i) {
        return this.danhSach[i];
    }

    public void set(int i, Hang h) {
        this.danhSach[i] = h;
    }

}
