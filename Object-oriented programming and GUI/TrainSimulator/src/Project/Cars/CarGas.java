package Project.Cars;

public class CarGas extends CarCargo {

    private String type;
    private int explosion_danger_zone;

    public CarGas(String sender, boolean security, double weight, double width, double height, String type, int explosion_danger_zone) {
        super(sender, security, weight, width, height);
        this.type = type;
        this.explosion_danger_zone = explosion_danger_zone;
    }
}