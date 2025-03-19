import java.util.Scanner;

public class TestAnimal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n========= Menu Utama =========");
            System.out.println("|| 1. Pilih Hewan            ||");
            System.out.println("|| 2. Keluar                 ||");
            System.out.println("==============================");
            System.out.print("Pilih opsi (1-2): ");
            int mainOption = scanner.nextInt();

            if (mainOption == 2) {
                System.out.println("Keluar dari program.");
                scanner.close();
                break;
            } else if (mainOption == 1) {
                System.out.println("\nPilih jenis hewan:");
                System.out.println("1. Sapi");
                System.out.println("2. Ayam");
                System.out.print("Masukkan pilihan hewan (1-2): ");
                int animalChoice = scanner.nextInt();

                Animal animal = null;
                if (animalChoice == 1) {
                    System.out.print("Masukkan kondisi isi perut sapi (0 - 3): ");
                    double entrails = scanner.nextDouble();
                    animal = new Cow(entrails);
                } else if (animalChoice == 2) {
                    System.out.print("Masukkan kondisi isi perut ayam (0 - 3): ");
                    double entrails = scanner.nextDouble();
                    animal = new Chicken(entrails);
                } else {
                    System.out.println("Pilihan hewan tidak valid.");
                    continue;
                }

                while (true) {
                    System.out.println("\n====== Menu Hewan ======");
                    System.out.println("|| 1. Beri Makan      ||");
                    System.out.println("|| 2. Suara Hewan     ||");
                    System.out.println("|| 3. Kembali ke Menu ||");
                    System.out.println("========================");
                    System.out.print("Pilih opsi (1-3): ");
                    int actionOption = scanner.nextInt();

                    switch (actionOption) {
                        case 1:
                            System.out.println("Memberi makan...");
                            if (animal instanceof Chicken) {
                                System.out.println("Pilih pakan untuk ayam:");
                                for (Feed feeds : Feed.values()) {
                                    System.out.println(feeds.ordinal() + 1 + ". " + feeds);
                                }
                                System.out.print("Masukkan pilihan pakan (1-4)" + Feed.values().length + "): ");
                                int feedChoice = scanner.nextInt();
                                if (feedChoice >= 1 && feedChoice <= Feed.values().length) {
                                    Feed feed = Feed.values()[feedChoice - 1];
                                    ((Chicken) animal).Feeding(feed);
                                } else {
                                    System.out.println("Pilihan pakan tidak valid.");
                                }
                            } else {
                                animal.Feeding();
                            }
                            break;
                        case 2:
                            System.out.println("Suara hewan:");
                            animal.Voice();
                            break;
                        case 3:
                            System.out.println("Kembali ke menu utama...");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    }

                    if (actionOption == 3) {
                        break;
                    }
                }
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}

