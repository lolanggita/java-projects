import java.util.Scanner;

public class Countchr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama kamu: ");
        String nama = scanner.nextLine();
        
        int jumlahKarakter = nama.length();
        
        System.out.print("Masukkan nilai A: ");
        int A = scanner.nextInt();

        System.out.print("Masukkan nilai B: ");
        int B = scanner.nextInt();

        int hasilA = A + jumlahKarakter;
        
        // Menampilkan hasil
        System.out.println("Jumlah karakter nama kamu: " + jumlahKarakter);
        System.out.println("Nilai A setelah ditambahkan jumlah karakter: " + hasilA);
        System.out.println("Nilai B: " + B);
        
        // Menangani kemungkinan pembagian dengan nol
        try {
            double hasilB = A / B; 
            System.out.println("Hasil A/B = " + hasilB);
        } catch (ArithmeticException e) {
            System.out.println("Error: Pembagian dengan nol tidak diperbolehkan.");
        } finally {
            scanner.close();
        }
    }
}