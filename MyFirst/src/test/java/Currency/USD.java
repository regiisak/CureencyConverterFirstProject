package Currency;

public class USD extends Coin{
    private final double value=3.52;

    @Override
    public double getValue() {
        return value;
    }
    @Override
    public double calculate(double d) {
         return d * getValue();
    }

}
