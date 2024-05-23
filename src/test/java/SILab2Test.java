import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    void EveryBranch() {
        List<Item> items = new ArrayList<>();

        // 1
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));

        // 2
        items = Arrays.asList(new Item("Item", null, 100, 0));

        List<Item> finalItems = items;
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(finalItems, 100));

        // 3
        items = Arrays.asList(new Item(null, "itemBarcode", 100, 0));
        List<Item> finalItems1 = items;
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(finalItems1, 100));

        // 4
        items = Arrays.asList(new Item(null, "0123", 350, 0.1f));
        assertTrue(SILab2.checkCart(items, 30));

        // 5
        items = Arrays.asList(new Item(null, "0123", 350, 0));
        assertFalse(SILab2.checkCart(items, 30));
    }

    @Test
    void MultipleCondition() {
        // T T T
        Item item = new Item("item", null, 350, 0.1F);
        item.setBarcode("012345");
        assertTrue(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');

        // T F X
        item.setPrice(350);
        item.setDiscount(0);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');

        // T T F
        item.setDiscount(0.1F);
        item.setBarcode("112345");
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');

        // F X X
        item.setPrice(200);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }
}