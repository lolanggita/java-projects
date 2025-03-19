package Yippie;
import java.util.Scanner;

class AirConditioner {
    private boolean isOn;
    private int suhu;
    private String mode;
    private int fanSpeed;
    private String airDirection;

    public AirConditioner() {
        this.isOn = false;
        this.suhu = 24; 
        this.mode = "cool"; 
        this.fanSpeed = 1;
        this.airDirection = "Straight"; 
    }

    public void hidupkan() {
       isOn = true;
        System.out.println("AC telah dihidupkan.");
    }

    public void matikan() {
       isOn = false;
        System.out.println("AC telah dimatikan.");
    }

    public void setSuhu(int suhu) {
        if (isOn) {
            this.suhu = suhu;
            System.out.println("Suhu diatur menjadi " + suhu + "°C.");
        } else {
            System.out.println("Tidak bisa mengatur suhu. AC mati.");
        }
    }

    public void setMode(String mode) {
        if (isOn) {
            this.mode = mode;
            System.out.println("Mode diatur menjadi " + mode);
        } else {
            System.out.println("Tidak bisa mengganti mode. AC mati.");
        }
    }

    public void setfanSpeed(int kecepatan) {
        if (isOn) {
            this.fanSpeed = kecepatan;
            System.out.println("Kecepatan kipas diatur menjadi " + kecepatan);
        } else {
            System.out.println("Tidak bisa mengganti kecepatan kipas. AC mati.");
        }
    }

    public void setairDirection(String arah) {
        if (isOn) {
            this.airDirection = arah;
            System.out.println("Arah udara diatur menjadi " + arah);
        } else {
            System.out.println("Tidak bisa mengganti arah udara. AC mati.");
        }
    }

    public void tampilkanStatus() {
        if (isOn) {
            System.out.println("AC Hidup");
            System.out.println("Suhu: " + suhu + "°C");
            System.out.println("Mode: " + mode);
            System.out.println("Kecepatan Kipas: " + fanSpeed);
            System.out.println("Arah Udara: " + airDirection);
        } else {
            System.out.println("AC mati");
        }
    }
}

class RemoteControl {
    private AirConditioner ac;

    public RemoteControl(AirConditioner ac) {
        this.ac = ac;
    }

    public void hidupkan() {
        ac.hidupkan();
    }

    public void matikan() {
        ac.matikan();
    }

    public void ubahSuhu(int suhu) {
        ac.setSuhu(suhu);
    }

    public void ubahMode(String mode) {
        ac.setMode(mode);
    }

    public void ubahfanSpeed(int kecepatan) {
        ac.setfanSpeed(kecepatan);
    }

    public void ubahairDirection(String arah) {
        ac.setairDirection(arah);
    }
}

class SensorSuhu {
    private int suhuSaatIni;

    public SensorSuhu() {
        this.suhuSaatIni = 25;
    }

    public void deteksiSuhu() {
        System.out.println("Suhu ruangan saat ini adalah " + suhuSaatIni + "°C.");
    }

    public void setSuhuSaatIni(int suhu) {
        this.suhuSaatIni = suhu;
    }

    public int getSuhuSaatIni() {
        return suhuSaatIni;
    }
}


public class Main {
    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();
        RemoteControl remote = new RemoteControl(ac);
        SensorSuhu sensor = new SensorSuhu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Remote AC ===");
            System.out.println("1. Hidupkan AC");
            System.out.println("2. Matikan AC");
            System.out.println("3. Atur Suhu");
            System.out.println("4. Ganti Mode");
            System.out.println("5. Atur Kecepatan Kipas");
            System.out.println("6. Atur Arah Udara");
            System.out.println("7. Cek Suhu Ruangan");
            System.out.println("8. Tampilkan Status AC");
            System.out.println("9. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    remote.hidupkan();
                    break;
                case 2:
                    remote.matikan();
                    break;
                case 3:
                    System.out.print("Masukkan suhu yang diinginkan: ");
                    int suhu = scanner.nextInt();
                    remote.ubahSuhu(suhu);
                    break;
                case 4:
                    System.out.print("Masukkan mode (Cooling/Heating/Fan): ");
                    String mode = scanner.next();
                    remote.ubahMode(mode);
                    break;
                case 5:
                    System.out.print("Masukkan kecepatan kipas (1: Low, 2: Medium, 3: High): ");
                    int kecepatan = scanner.nextInt();
                    remote.ubahfanSpeed(kecepatan);
                    break;
                case 6:
                    System.out.print("Masukkan arah udara (Straight/Upward/Downward): ");
                    String arah = scanner.next();
                    remote.ubahairDirection(arah);
                    break;
                case 7:
                    sensor.deteksiSuhu();
                    break;
                case 8:
                    ac.tampilkanStatus();
                    break;
                case 9:
                    System.out.println("Keluar...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }
}
