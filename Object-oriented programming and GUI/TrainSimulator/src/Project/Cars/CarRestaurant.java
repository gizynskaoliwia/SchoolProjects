package Project.Cars;

public class CarRestaurant extends Car {
    private boolean needElectricity;
    private int numberOfCooks;
    private int numberOfTables;

    public CarRestaurant(String sender, boolean secured, double weight, boolean needElectricity, int numberOfCooks, int numberOfTables) {
        super(sender, secured, weight);
        this.needElectricity = needElectricity;
        this.numberOfCooks = numberOfCooks;
        this.numberOfTables = numberOfTables;
    }

}