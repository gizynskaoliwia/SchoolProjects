package Project.Cars;

public class CarRefrigirator extends CarCargo {
    private boolean needElectricity;
    private int temperature;
    private String contnent;

    public CarRefrigirator(String sender, boolean security, double weight, double width, double height, int temperature, String contnent) {
        super(sender, security, weight, width, height);
        this.needElectricity = true;
        this.contnent = contnent;
        this.temperature = temperature;
    }

}