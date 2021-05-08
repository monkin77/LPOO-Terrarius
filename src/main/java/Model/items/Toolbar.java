package Model.items;

import Model.Dimensions;

import java.util.HashMap;
import java.util.Map;


public class Toolbar {
    private Map<Integer, Item> toolBar;
    private Integer activeItemIdx;
    private final Integer maxSlots = 9;
    private final Dimensions dimensions = new Dimensions(9, 128);
    private final int toolbarCellLength = 5;
    private final int toolbarSeparatorWidth = 1;

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
        if (index < 1 || index > 9) return;
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

    public int getToolbarCellLength() {
        return this.toolbarCellLength;
    }

    public int getToolbarSeparatorWidth() {
        return toolbarSeparatorWidth;
    }

    public Integer getMaxSlots() {
        return maxSlots;
    }
}
