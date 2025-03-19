package Lingkaran;

public class TestLingkaran {
    public static void main(String[] args) {
        Lingkaran L1 = new Lingkaran();
        System.out.println("jari-jari dari Lingkaran L1 adalah " + L1.getRadius());

        Lingkaran L2 = new Lingkaran(3.5);
        System.out.println("jari-jari dari Lingkaran L2 adalah " + L2.getRadius());

        Lingkaran L3 = new Lingkaran(3.5, "kuning");
        System.out.println("jari-jari dan warna dari Lingkaran L3 adalah " + L3.getRadius() + " dan " + L3.getWarna());

        Lingkaran L4 = new Lingkaran(7);
        System.out.println("jari-jari awal dari Lingkaran L4 adalah " + L4.getRadius());

        L1.setRadius(10);
        System.out.println(" setelah ditambah 3 jari-jari Lingkaran L4 menjadi " + L1.getRadius());
    }

}
