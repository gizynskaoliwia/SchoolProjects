package Project.Cars;

public class CarCargo extends Car {

    private double height;
    private double width;


    public CarCargo(String sender, boolean security, double weight, double width, double height) {
        super(sender, security, weight);
        this.height = height;
        this.width = width;
    }
}