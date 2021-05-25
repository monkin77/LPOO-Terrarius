package Terrarius.Model;


import Terrarius.Model.elements.blocks.*;

import java.util.*;

public class BlockPouch {

    private final List<String> blockNames = new ArrayList<>();
    private final List<Integer> blockQuantities = new ArrayList<>();
    private int currentBlock;

    public BlockPouch(){
        blockNames.add("DirtBlock");
        blockNames.add("StoneBlock");
        blockNames.add("WoodBlock");
        currentBlock = 0;
        for (int i = 0; i < blockNames.size(); i++) blockQuantities.add(0);
    }

    public void setBlock(Block block, int val){
        int index = blockNames.indexOf(block.getName());
        blockQuantities.set(index, val);
    }

    public int getBlock(Block block){
        int index = blockNames.indexOf(block.getName());
        return blockQuantities.get(index);
    }

    public void incrementBlock(Block block){
        int index = blockNames.indexOf(block.getName());
        blockQuantities.set(index, blockQuantities.get(index) + 1);
    }

    public void decrementBlock(Block block){
        int index = blockNames.indexOf(block.getName());
        blockQuantities.set(index, blockQuantities.get(index) - 1);
    }

    public void cycleCurrentBlock(){
        currentBlock = (currentBlock + 1) % blockNames.size();
    }

    public String getCurrentBlockName(){
        return this.blockNames.get(currentBlock);
    }

    public Integer getCurrentBlockQuantity(){
        return blockQuantities.get(currentBlock);
    }

    public Block generateCurrentBlock(Position position){
        switch (this.blockNames.get(this.currentBlock)) {
            case "DirtBlock":  return new DirtBlock(position);
            case "StoneBlock": return new StoneBlock(position);
            case "WoodBlock":  return new WoodBlock(position);
            default: return null;
        }
    }

}
