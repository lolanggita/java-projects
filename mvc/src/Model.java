public class Model {
    
    private String nama;
    private String alamat;
    private String jurusan;
    private Integer usia;

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }
    
    public String getAlamat() {
        return alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public Integer getUsia() {
        return usia;
    }

}
