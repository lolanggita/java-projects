abstract class Animal {
    protected double entrails;

    public Animal(double entrails) {
        this.entrails = entrails;
    }

    public void Feeding() {
        if (entrails >= 0 && entrails < 3.0) {
            entrails += 1.0 / 3.0;
            System.out.println("Kasi makan hewan... Isi perut sekarang: " + entrails);
        } else {
            System.out.println("Hewan sudah kenyang, harus pup terlebih dahulu!");
            poop();
        }
    }

    public void poop() {
        entrails -= 1.0 / 3.0;
        System.out.println("Hewan pup... Isi perut sekarang: " + entrails);
    }

    public abstract void Voice();
}
