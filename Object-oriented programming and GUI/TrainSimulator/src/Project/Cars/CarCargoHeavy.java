package Project.Cars;

public class CarCargoHeavy extends Car {

    private boolean harder_roof;
    private double extra_capacity;

    public CarCargoHeavy(String sender, boolean security, double weight, boolean harder_roof, double extra_capacity) {
        super(sender, security, weight);
        this.extra_capacity = extra_capacity;
        this.harder_roof = harder_roof;
    }

}