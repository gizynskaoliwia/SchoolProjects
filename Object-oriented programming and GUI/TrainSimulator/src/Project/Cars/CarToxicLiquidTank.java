package Project.Cars;

public class CarToxicLiquidTank extends CarCargoHeavy {

    private String fluidName;
    private int capacity;
    private boolean securityLayet;
    private double maxTransportTime;


    public CarToxicLiquidTank(String sender, boolean security, double weight, boolean harder_roof, double extra_capacity, String fluidName, int capacity, boolean securityLayet, double maxTransportTime) {
        super(sender, security, weight, harder_roof, extra_capacity);
        this.fluidName = fluidName;
        this.capacity = capacity;
        this.securityLayet = securityLayet;
        this.maxTransportTime = maxTransportTime;
    }
}