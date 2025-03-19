public class Cow extends Animal {
    public Cow(double entrails){
        super(entrails);
    }

    @Override
    public void Voice() {
        System.out.println("booo doooo...");
    }

    public void Feeding(Feed feeds) {
        if (entrails >= 0 && entrails < 3.0) {
            entrails += 1.0 / 3.0;
            System.out.println("Kasi makan sapi dengan rumput... Isi perut sapi sekarang: " + entrails);
        } else {
            System.out.println("Sapi sudah kenyang, harus pup terlebih dahulu!");
            poop();
        }
    }
}
