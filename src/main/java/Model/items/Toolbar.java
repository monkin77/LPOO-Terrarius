package Model.items;

import java.util.HashMap;
import java.util.Map;

public class Toolbar {
    private Map<Integer, Item> toolBar;
    private Integer activeItemIdx;
    private final Integer maxSlots = 9;

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

    /**
     * Finds the first free slot on the toolbar
     * @return key of the first free slot. If there is no free slot, returns -1
     */
    public Integer findFreeSlot() {
        for(Integer i = 1; i <= maxSlots; i++) {
            if(!this.toolBar.containsKey(i))
                return i;
        }
        return -1;
    }
}
