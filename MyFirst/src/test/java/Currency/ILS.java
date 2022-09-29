package Currency;

public class ILS extends Coin {
    private final double value=0.28;

    @Override
    public double getValue() {
        return value;
    }
    @Override
    public double calculate(double d) {
         return d * getValue();
    }
}
