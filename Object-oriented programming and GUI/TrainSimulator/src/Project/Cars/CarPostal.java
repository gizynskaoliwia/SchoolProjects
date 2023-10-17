package Project.Cars;

public class CarPostal extends Car {
    private boolean needElectricity;
    private boolean sortingDeliveries;

    public CarPostal(String sender, boolean secured, double weight, boolean needElectricity, boolean sortingDeliveries) {
        super(sender, secured, weight);
        this.needElectricity = needElectricity;
        this.sortingDeliveries = sortingDeliveries;
    }
}