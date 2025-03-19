package ACRemot;

// RemoteControl class
public class RemoteControl {
    private ACUnit acUnit1;
    private ACUnit acUnit2;

    public RemoteControl(ACUnit acUnit1, ACUnit acUnit2) {
        this.acUnit1 = acUnit1;
        this.acUnit2 = acUnit2;
    }

    public void controlACUnit1(int temperature, String mode, int fanSpeed, String airDirection) {
        acUnit1.setTemperature(temperature);
        acUnit1.setMode(mode);
        acUnit1.setFanSpeed(fanSpeed);
        acUnit1.setAirDirection(airDirection);
    }

    public void controlACUnit2(int temperature, String mode, int fanSpeed, String airDirection) {
        acUnit2.setTemperature(temperature);
        acUnit2.setMode(mode);
        acUnit2.setFanSpeed(fanSpeed);
        acUnit2.setAirDirection(airDirection);
    }

    public void displayACUnitStatus(int acUnitNumber) {
        if (acUnitNumber == 1) {
            acUnit1.displayStatus();
        } else if (acUnitNumber == 2) {
            acUnit2.displayStatus();
        }
    }

    public void checkRoomTemperature() {
        int roomTemperature = SensorRuangan.getRoomTemperature();
        System.out.println("Room Temperature: " + roomTemperature);
    }
}

