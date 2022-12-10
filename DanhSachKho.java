public class DanhSachKho {
    public Kho[] danhSach;
    public int soLuong = 0;

    public DanhSachKho(Kho... danhSachNhap) {
        this.soLuong = danhSachNhap.length;
        danhSach = new Kho[this.soLuong];
        int i = 0;
        for (Kho k : danhSachNhap) {
            danhSach[i] = k;
        }
    }

    public void Nhap(Kho k) {
        this.soLuong += 1;
        Kho[] danhSachMoi = new Kho[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            if (i == this.soLuong - 1) {
                danhSachMoi[i] = k;
            } else {
                danhSachMoi[i] = this.danhSach[i];
            }
        }
        this.danhSach = danhSachMoi;
    }

    public Kho get(int i) {
        return this.danhSach[i];
    }

    public void set(int i, Kho k) {
        this.danhSach[i] = k;
    }
}
