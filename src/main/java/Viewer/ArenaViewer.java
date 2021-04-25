package Viewer;

import GUI.GUI;
import Model.Position;
import Model.arena.Arena;
import Model.elements.Element;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Viewer.Image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaViewer {

    private final Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private final Map<Class, ElementViewer> blockCache = new HashMap<>();
    private HeroViewer heroViewer = new HeroViewer();

    private final GUI gui;

    public ArenaViewer(GUI gui){
        this.gui = gui;
    }

    public void draw(Arena arena){ //Arena argument

        //Do enemy too

        for(Block block : arena.getBlocks()){
            if (!blockCache.containsKey(blockCache)){
                blockCache.put(block.getClass(), new BlockViewer(block));
            }
            ElementViewer viewer = blockCache.get(block.getClass());
            viewer.draw(block, gui);
        }

        heroViewer.draw(arena.getHero(), gui);

    }

}
