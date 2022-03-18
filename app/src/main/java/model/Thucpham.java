package model;

import java.io.Serializable;

public class Thucpham implements Serializable {
    //tao khuon co nhung thuoc tinh dc tra ve
    public int ID;
    public String Tenthucpham;
    public Integer Giathucpham;
    public String Hinhanhthucpham;
    public String Motathucpham;
    public int IDThucpham;

    public Thucpham(int ID, String tenthucpham, Integer giathucpham, String hinhanhthucpham, String motathucpham, int IDThucpham) {
        this.ID = ID;
        Tenthucpham = tenthucpham;
        Giathucpham = giathucpham;
        Hinhanhthucpham = hinhanhthucpham;
        Motathucpham = motathucpham;
        this.IDThucpham = IDThucpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenthucpham() {
        return Tenthucpham;
    }

    public void setTenthucpham(String tenthucpham) {
        Tenthucpham = tenthucpham;
    }

    public Integer getGiathucpham() {
        return Giathucpham;
    }

    public void setGiathucpham(Integer giathucpham) {
        Giathucpham = giathucpham;
    }

    public String getHinhanhthucpham() {
        return Hinhanhthucpham;
    }

    public void setHinhanhthucpham(String hinhanhthucpham) {
        Hinhanhthucpham = hinhanhthucpham;
    }

    public String getMotathucpham() {
        return Motathucpham;
    }

    public void setMotathucpham(String motathucpham) {
        Motathucpham = motathucpham;
    }

    public int getIDThucpham() {
        return IDThucpham;
    }

    public void setIDThucpham(int IDThucpham) {
        this.IDThucpham = IDThucpham;
    }
}
