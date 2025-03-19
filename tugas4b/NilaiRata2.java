package tugas4b;

public class NilaiRata2 {
    public static void main(String[] args) {
        int a = 24;
        int b = 14;
        int c = 6;
        int d = 523;
        int e = 60;

        // menghitung nilai max, min, dan rata-rata
        int max = Math.max(a, Math.max(b, Math.max(c, Math.max(d, e))));
        int min = Math.min(a, Math.max(b, Math.max(c, Math.max(d, e))));
        double rataRata = (a + b + c + d + e) / 5.0;

        // menampilkan hasil 
        System.out.println("Nilai maksimum: " + max);
        System.out.println("Nilai minimum: " + min);
        System.out.println("Nilai rata-rata: " + rataRata);
    }
}
