package dev.danvega.orders;

public class DiscountPolicy {

    private final String code;
    private final int percentOff;
    private final double minimumSubtotal;

    public DiscountPolicy(String code, int percentOff, double minimumSubtotal) {
        this.code = code;
        this.percentOff = percentOff;
        this.minimumSubtotal = minimumSubtotal;
    }

    public String getCode() {
        return code;
    }

    public int getPercentOff() {
        return percentOff;
    }

    public boolean appliesTo(double subtotal) {
        return subtotal >= minimumSubtotal;
    }
}
