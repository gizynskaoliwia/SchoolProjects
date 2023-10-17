package Project.Cars;

public class CarToxicMaterials extends CarCargoHeavy {
    private String material;
    private int numberOfAirFilters;

    public CarToxicMaterials(String sender, boolean security, double weight, boolean harder_roof, double extra_capacity, String material, int numberOfAirFilters) {
        super(sender, security, weight, harder_roof, extra_capacity);
        this.material = material;
        this.numberOfAirFilters = numberOfAirFilters;
    }
}