package model;

public class Loaitp {
    public int id;
    public String Tenloaitp;
    public String Hinhanhloaitp;

    public Loaitp(int id, String tenloaitp, String hinhanhloaitp) {
        this.id = id;
        Tenloaitp = tenloaitp;
        Hinhanhloaitp = hinhanhloaitp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaitp() {
        return Tenloaitp;
    }

    public void setTenloaitp(String tenloaitp) {
        Tenloaitp = tenloaitp;
    }

    public String getHinhanhloaitp() {
        return Hinhanhloaitp;
    }

    public void setHinhanhloaitp(String hinhanhloaitp) {
        Hinhanhloaitp = hinhanhloaitp;
    }
}
