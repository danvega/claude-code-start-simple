package dev.danvega.orders;

import java.math.BigDecimal;

public class PriceTag {

    private final BigDecimal originalPrice;

    public PriceTag(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
}
