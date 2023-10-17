package Project.Cars;

public abstract class Car {

    private String sender;
    private boolean secured;
    private double weight;
    private static int globalId = 0;
    private int carId;

    public Car(String sender, boolean secured, double weight) {
        this.carId = ++globalId;
        this.secured = secured;
        this.weight = weight;
        this.sender = sender;
    }

    public int getId() {
        return carId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSecured(boolean secured) {
        this.secured = secured;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public enum CarKinds{
        PORTEL_POSTAL,
        CARGO,
        CARGO_HEAVY,
        EXPLOSIVES,
        GAS_TANK,
        LIQUID_TANK,
        PASSENGER,
        POSTAL,
        REFRIGIRATOR,
        RESTAURANT,
        TOXIC_LIQUIDS,
        TOXIC_MATERIALS
    }


}