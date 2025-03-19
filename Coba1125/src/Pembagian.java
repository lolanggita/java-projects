import java.util.Scanner;

public class Pembagian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Meminta pengguna untuk memasukkan nilai A
        System.out.print("Masukkan nilai A: ");
        double A = scanner.nextDouble();
        
        // Meminta pengguna untuk memasukkan nilai B
        System.out.print("Masukkan nilai B: ");
        double B = scanner.nextDouble();
        
        try {
            // Melakukan pembagian A/B
            double hasil = A / B;
            System.out.println("Hasil A/B = " + hasil);
        } catch (ArithmeticException e) {
            // Menangani pembagian dengan nol
            System.out.println("Error: Pembagian dengan nol tidak diperbolehkan.");
        } finally {
            // Menutup scanner
            scanner.close();
        }
    }
}