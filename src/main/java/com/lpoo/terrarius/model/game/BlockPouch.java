package com.lpoo.terrarius.model.game;

import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.model.game.items.tools.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlockPouch {

    private final List<String> blockNames = new ArrayList<>();
    private final List<Integer> blockQuantities = new ArrayList<>();
    private int currentBlock;

    public BlockPouch(){
        loadBlocks();
        this.currentBlock = 0;
        for (int i = 0; i < blockNames.size(); i++) blockQuantities.add(0);
    }

    public void incrementBlock(Block block){
        int index = blockNames.indexOf(block.getComponentName());
        blockQuantities.set(index, blockQuantities.get(index) + 1);
    }

    public void decrementBlock(Block block){
        int index = blockNames.indexOf(block.getComponentName());
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

    public Block generateCurrentBlock(Position position) {
        try {
            return new Block(position, this.blockNames.get(this.currentBlock));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    void loadBlocks() {
        try {
            URL resource = Tool.class.getResource("/assets/blocks/Names.txt");
            Scanner scanner = new Scanner(new File(resource.toURI()));

            while (scanner.hasNext()) {
                blockNames.add(scanner.next());
            }

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
