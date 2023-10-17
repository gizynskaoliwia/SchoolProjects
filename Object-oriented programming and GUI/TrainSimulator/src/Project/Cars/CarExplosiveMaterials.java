package Project.Cars;

public class CarExplosiveMaterials extends CarCargoHeavy {
    private boolean hardened_roof;
    private boolean special_symbols;

    public CarExplosiveMaterials(String sender, boolean security, double weight, boolean harder_roof, double extra_capacity, boolean hardened_roof, boolean special_symbols) {
        super(sender, security, weight, harder_roof, extra_capacity);
        this.hardened_roof = hardened_roof;
        this.special_symbols = special_symbols;
    }
}