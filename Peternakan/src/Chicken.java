public class Chicken extends Animal{
    public Chicken(double entrails){
        super(entrails);
    }

    @Override
    public void Voice() {
        System.out.println("kok kok kok...");
    }

    public void Feeding(Feed feeds) {
        if (entrails >= 0 && entrails < 3.0) {
            entrails += 1.0 / 3.0;
            System.out.println("Kasi makan ayam dengan " + feeds + "... Isi perut Ayam sekarang: " + entrails);
        } else {
            System.out.println("Ayam sudah kenyang, harus pup terlebih dahulu!");
            poop();
        }
    }
}
