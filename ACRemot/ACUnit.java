package ACRemot;

// ACUnit class
public class ACUnit {
    private int temperature;
    private String mode; // cooling, dry, fan
    private int fanSpeed; // 1, 2, 3
    private String airDirection; // downward, straight, upward

    public ACUnit(int temperature, String mode, int fanSpeed, String airDirection) {
        this.temperature = temperature;
        this.mode = mode;
        this.fanSpeed = fanSpeed;
        this.airDirection = airDirection;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public void setAirDirection(String airDirection) {
        this.airDirection = airDirection;
    }

    public void displayStatus() {
        System.out.println("AC Unit Status:");
        System.out.println("Temperature: " + temperature);
        System.out.println("Mode: " + mode);
        System.out.println("Fan Speed: " + fanSpeed);
        System.out.println("Air Direction: " + airDirection);
    }
}
