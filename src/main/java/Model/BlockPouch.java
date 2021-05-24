package Model;

import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;

import java.util.*;

public class BlockPouch {

    private final List<Class> blockClasses = new ArrayList<>();
    private final List<Integer> blockQuantities = new ArrayList<>();
    private int currentBlock;

    public BlockPouch(){
        blockClasses.add(DirtBlock.class);
        blockClasses.add(StoneBlock.class);
        blockClasses.add(WoodBlock.class);
        currentBlock = 0;
        for (int i = 0; i < blockClasses.size(); i++) blockQuantities.add(0);
    }

    public void setBlock(Block block, int val){
        int index = blockClasses.indexOf(block.getClass());
        blockQuantities.set(index, val);
    }

    public int getBlock(Block block){
        int index = blockClasses.indexOf(block.getClass());
        return blockQuantities.get(index);
    }

    public void incrementBlock(Block block){
        int index = blockClasses.indexOf(block.getClass());
        blockQuantities.set(index, blockQuantities.get(index) + 1);
    }

    public void decrementBlock(Block block){
        int index = blockClasses.indexOf(block.getClass());
        blockQuantities.set(index, blockQuantities.get(index) - 1);
    }

    public void cycleCurrentBlock(){
        currentBlock = (currentBlock + 1) % blockClasses.size();
    }

    public String getCurrentBlockName(){
        if (this.blockClasses.get(currentBlock).equals(DirtBlock.class)){
            return "DirtBlock";
        }
        else if (this.blockClasses.get(currentBlock).equals(WoodBlock.class)){
            return "WoodBlock";
        }
        else if (this.blockClasses.get(currentBlock).equals(StoneBlock.class)){
            return "StoneBlock";
        }
        else{
            return "NullBlock"; //aka no block
        }
    }

    public Integer getCurrentBlockQuantity(){
        return blockQuantities.get(currentBlock);
    }

}
