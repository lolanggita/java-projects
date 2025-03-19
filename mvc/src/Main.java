public class Main {
    
    public static void main(String[] args) {
        
        Model mo = new Model();
        View vi = new View();

        Controller cc = new Controller(mo, vi);

        cc.setNama("Lola Anggita");
        cc.setAlamat("Johor");
        cc.setJurusan("RPL");
        cc.setUsia(17);
        
        cc.viewTampil();
    }
}
