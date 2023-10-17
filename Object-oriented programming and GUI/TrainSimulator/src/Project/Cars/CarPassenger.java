package Project.Cars;

public class CarPassenger extends Car {
    private boolean needElectricity;
    private boolean air_conditioning;
    private int numberOfToilets;

    public CarPassenger(String sender, boolean secured, double weight, boolean needElectricity, boolean air_conditioning, int numberOfToilets) {
        super(sender, secured, weight);
        this.needElectricity = needElectricity;
        this.air_conditioning = air_conditioning;
        this.numberOfToilets = numberOfToilets;
    }

    public void setNeedElectricity(boolean needElectricity) {
        this.needElectricity = needElectricity;
    }
}