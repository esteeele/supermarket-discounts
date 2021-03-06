package kata.supermarket.discounts;

import kata.supermarket.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BuyOneGetOneFree implements Discount {

    private static final Map<UUID, Integer> itemCounts = new HashMap<>();

    @Override
    /**
     * TODO This could be generalised to also support the buy 3 for 2 - some sort of MultiBuyDiscount or something
     * because likely to be a lot of repetitive code (i.e. which includes the map but some kind of rules are injected in
     * that determine how to apply the discount)
     */
    public BigDecimal applyDiscount(List<Item> basket) {
        BigDecimal discount = BigDecimal.ZERO;
        for (Item item : basket) {
            //need to know what an item is to calculate discounts properly
            if (item instanceof ItemByUnit) {
                if (itemCounts.containsKey(item.itemId())) {
                    int count = itemCounts.get(item.itemId()) + 1;
                    if (count % 2 == 0) {
                        discount = discount.add(item.price());
                    }
                    itemCounts.put(item.itemId(), count);
                } else {
                    itemCounts.put(item.itemId(), 1);
                }
            }
        }

        return discount;
    }
}
