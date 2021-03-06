package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public interface Item {
    BigDecimal price();
    UUID itemId();
    String name();
    BigDecimal amountOf();
}
