public class mahasiswa {
    private String nama;
    private int nim;
    
    public mahasiswa (String nama, int nim){
        this.nama = nama;
        this.nim = nim;

        System.out.println("Mahasiswa S1 Sistem Informasi: ");
        System.out.println("Nama : " + nama);
        System.out.println("Nim  : " + nim);
    }

    public String getNama(){
        return nama;
    }

    public int getNim(){
        return nim;
    }
}