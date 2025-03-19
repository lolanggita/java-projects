package ACRemot;
import java.util.Scanner;

// ACSystem class
public class ACSystem {
    private RemoteControl remoteControl;
    private ACUnit acUnit1;
    private ACUnit acUnit2;
    private Scanner scanner;

    public ACSystem() {
        acUnit1 = new ACUnit(20, "cooling", 2, "straight");
        acUnit2 = new ACUnit(20, "cooling", 2, "straight");
        remoteControl = new RemoteControl(acUnit1, acUnit2);
        scanner = new Scanner(System.in);
    }

    public void startSystem() {
        System.out.println("AC System Started!");
        remoteControl.displayACUnitStatus(1);
        remoteControl.displayACUnitStatus(2);

        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1. Control AC Unit 1");
            System.out.println("2. Control AC Unit 2");
            System.out.println("3. Check Room Temperature");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controlACUnit(1);
                    break;
                case 2:
                    controlACUnit(2);
                    break;
                case 3:
                    remoteControl.checkRoomTemperature();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void controlACUnit(int acUnitNumber) {
        System.out.println("Enter temperature (1-30):");
        int temperature = scanner.nextInt();

        System.out.println("Enter mode (cooling, dry, fan):");
        String mode = scanner.next();

        System.out.println("Enter fan speed (1, 2, 3):");
        int fanSpeed = scanner.nextInt();

        System.out.println("Enter air direction (downward, straight, upward):");
        String airDirection = scanner.next();

        if (acUnitNumber == 1) {
            remoteControl.controlACUnit1(temperature, mode, fanSpeed, airDirection);
        } else {
            remoteControl.controlACUnit2(temperature, mode, fanSpeed, airDirection);
        }

        remoteControl.displayACUnitStatus(acUnitNumber);
    }

    public static void main(String[] args) {
        ACSystem acSystem = new ACSystem();
        acSystem.startSystem();
    }
}
