package Lingkaran;

public class Lingkaran {
    private double jari2;
    private String warna;

    public Lingkaran(){
        jari2 = 1.0;
        warna = "merah";
    }

    public Lingkaran(double r){
        jari2 = r;
        warna = "merah";
    }

    public Lingkaran(double r, String c){
        jari2 = r;
        warna = c;
    }

    public double getRadius(){
        return jari2;
    }

    public String getWarna(){
        return warna;
    }

    public void setRadius(double r){
        jari2 = r;
    }
}
