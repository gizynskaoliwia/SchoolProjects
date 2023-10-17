package Project.Cars;

public class CarPorterPostal extends Car {
    private int maxBaggageNumber;
    private int maxDeliveryNumber;

    public CarPorterPostal(String sender, boolean secured, double weight, int maxBaggageNumber, int maxDeliveryNumber) {
        super(sender, secured, weight);
        this.maxBaggageNumber = maxBaggageNumber;
        this.maxDeliveryNumber = maxDeliveryNumber;
    }

    public int getMaxBaggageNumber() {
        return maxBaggageNumber;
    }

    public void setMaxBaggageNumber(int maxBaggageNumber) {
        this.maxBaggageNumber = maxBaggageNumber;
    }

    public int getMaxDeliveryNumber() {
        return maxDeliveryNumber;
    }

    public void setMaxDeliveryNumber(int maxDeliveryNumber) {
        this.maxDeliveryNumber = maxDeliveryNumber;
    }

    @Override
    public String toString() {
        return "CarPorterPostal{" +
                "maxBaggageNumber=" + maxBaggageNumber +
                ", maxDeliveryNumber=" + maxDeliveryNumber +
                '}';
    }
}
