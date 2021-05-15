package Model;

import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;

import java.util.*;

public class BlockPouch {

    private final List<String> blockNames = new ArrayList<>(); //TODO maybe not string and just class?
    private final List<Integer> blockQuantities = new ArrayList<>(); //TODO a list of pairs could also be used
    private int currentBlock;

    BlockPouch(){
        blockNames.add(DirtBlock.class.toString());
        blockNames.add(StoneBlock.class.toString());
        blockNames.add(WoodBlock.class.toString());
        currentBlock = 0;
        for (int i = 0; i < blockNames.size(); i++) blockQuantities.add(0);
    }

    public void setBlock(Block block, int val){
        int index = blockNames.indexOf(block.getClass().getName());
        blockQuantities.set(index, val);
    }

    public int getBlock(Block block){
        int index = blockNames.indexOf(block.getClass().getName());
        return blockQuantities.get(index);
    }

    public void incrementBlock(Block block){
        int index = blockNames.indexOf(block.getClass().getName());
        blockQuantities.set(index, blockQuantities.get(index) + 1);
    }

    public void decrementBlock(Block block){
        int index = blockNames.indexOf(block.getClass().getName());
        blockQuantities.set(index, blockQuantities.get(index) - 1);
    }

    public void cycleCurrentBlock(){
        currentBlock = (currentBlock + 1) % blockNames.size();
    }

    public String getCurrentBlockName(){
        return blockNames.get(currentBlock);
    }

    public int getCurrentBlockQuantity(){
        return blockQuantities.get(currentBlock);
    }

}
