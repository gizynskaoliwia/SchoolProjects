package Project.Cars;

public class CarLiquidMaterials extends CarCargo {

    private String fluidName;
    private int capacity;

    public CarLiquidMaterials(String sender, boolean security, double weight, double width, double height, String fluidName, int capacity) {
        super(sender, security, weight, width, height);
        this.fluidName = fluidName;
        this.capacity = capacity;
    }
}