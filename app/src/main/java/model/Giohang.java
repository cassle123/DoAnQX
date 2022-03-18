package model;

public class Giohang {
    public int idtp;
    public String tentp;
    public long giatp;
    public String hinhtp;
    public int soluongtp;

    public Giohang(int idtp, String tentp, long giatp, String hinhtp, int soluongtp) {
        this.idtp = idtp;
        this.tentp = tentp;
        this.giatp = giatp;
        this.hinhtp = hinhtp;
        this.soluongtp = soluongtp;
    }

    public int getIdtp() {
        return idtp;
    }

    public void setIdtp(int idtp) {
        this.idtp = idtp;
    }

    public String getTentp() {
        return tentp;
    }

    public void setTentp(String tentp) {
        this.tentp = tentp;
    }

    public long getGiatp() {
        return giatp;
    }

    public void setGiatp(long giatp) {
        this.giatp = giatp;
    }

    public String getHinhtp() {
        return hinhtp;
    }

    public void setHinhtp(String hinhtp) {
        this.hinhtp = hinhtp;
    }

    public int getSoluongtp() {
        return soluongtp;
    }

    public void setSoluongtp(int soluongtp) {
        this.soluongtp = soluongtp;
    }
}
