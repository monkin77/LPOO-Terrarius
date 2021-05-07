package Model.items;

import Model.Dimensions;

import java.util.HashMap;
import java.util.Map;

// TODO: STILL A MAP, NOT CHECKING RIGHT KEYS
public class Toolbar {
    private Map<Integer, Item> toolBar;
    private Integer activeItemIdx;
    private final Dimensions dimensions;
    private final Integer maxSlots;

    public Toolbar() {
        this.toolBar = new HashMap<>();
        maxSlots = 9;
        dimensions = new Dimensions(5, 37);
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

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Map<Integer, Item> getToolBar() {
        return toolBar;
    }

    public Integer getToolbarCellHeight() {
        return this.dimensions.getHeight() - 2;
    }

    public Integer getToolbarCellWidth() {
        return ( this.dimensions.getWidth() - 2 - (this.maxSlots-1) ) / this.maxSlots;
    }
}
