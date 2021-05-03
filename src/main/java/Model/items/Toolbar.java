package Model.items;

import java.util.HashMap;
import java.util.Map;

public class Toolbar {
    private Map<Integer, Item> toolBar;
    private Integer activeItemIdx;

    public Toolbar() {
        this.toolBar = new HashMap<>();
        activeItemIdx = 1;  // Just for testing. Should change this to 0 (unarmed) and change when pressing the numbers on the keyboard
    }

    public Integer getActiveItemIdx() {
        return activeItemIdx;
    }

    public Item getActiveItem() {
        return this.toolBar.get(activeItemIdx);
    }

    public Item getItem(Integer index) {
        return this.toolBar.get(index);
    }

    public void setItem(Integer index, Item item) {
        // Possibly check if there is already an item
        this.toolBar.put(index, item);
    }

    public void removeItem(Integer index) {
        this.toolBar.remove(index);
    }
}
