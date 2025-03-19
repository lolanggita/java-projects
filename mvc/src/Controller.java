public class Controller {
    
    private Model model;
    private View view;

    public Controller(Model m, View v){
        model = m;
        view = v;
    }

    public void setNama(String nama) {
        model.setNama(nama);
    }

    public void setAlamat(String alamat) {
        model.setAlamat(alamat);
    }

    public void setJurusan(String jurusan) {
        model.setJurusan(jurusan);
    }

    public void setUsia(Integer usia) {
        model.setUsia(usia);
    }

    public String getNama() {
        return model.getNama();
    }

    public String getAlamat() {
        return model.getAlamat();
    }

    public String getJurusan() {
        return model.getJurusan();
    }

    public Integer getUsia() {
        return model.getUsia();
    }

    public void viewTampil(){
        view.viewData(model.getNama(), model.getAlamat(), model.getJurusan(), model.getUsia());
    }
}
