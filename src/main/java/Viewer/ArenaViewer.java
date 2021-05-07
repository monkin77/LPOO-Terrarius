package Viewer;

import GUI.GUI;
import Model.arena.Arena;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Model.items.Item;
import Viewer.Image.AnimatedImage;
import Viewer.Image.ImageDimensions;
import Viewer.Image.StillImage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Viewer.ViewerConstants.*;

public class ArenaViewer {

    private final Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private final Map<Class, ElementViewer> blockCache = new HashMap<>();
    private final Map<Class, ItemViewer> itemCache = new HashMap<>();
    private HeroViewer heroViewer = new HeroViewer();

    private final GUI gui;

    public ArenaViewer(GUI gui){
        this.gui = gui;
    }

    public void update() {
        for (ElementViewer elementViewer : blockCache.values()){
            elementViewer.update();
        }

        for (ElementViewer elementViewer : enemyCache.values()){
            elementViewer.update();
        }

        heroViewer.update();
    }

    public void draw(Arena arena) throws IOException { //Arena argument

        this.gui.clear();

        for (int i = 0; i < arena.getWidth(); i++){
            for (int j = 0; j < arena.getHeight(); j++){
                gui.drawCharacter(i, j, ' ', SKY_COLOR, SKY_COLOR);
            }
        }

        for(Block block : arena.getBlocks()){
            if (!blockCache.containsKey(block.getClass())){
                blockCache.put(block.getClass(), new BlockViewer(block));
            }
            ElementViewer viewer = blockCache.get(block.getClass());
            viewer.draw(block, gui);
        }

        for(Enemy enemy : arena.getEnemies()){
            if (!enemyCache.containsKey(enemy.getClass())){
                enemyCache.put(enemy.getClass(), new EnemyViewer(enemy));
            }
            ElementViewer viewer = enemyCache.get(enemy.getClass());
            viewer.draw(enemy, gui);
        }

        Item item = arena.getHero().getToolBar().getActiveItem();
        if(item != null){
            if (!itemCache.containsKey(item.getClass())){
                itemCache.put(item.getClass(), new ItemViewer(item));
            }
            ItemViewer viewer = itemCache.get(item.getClass());

            viewer.draw(item, gui);
        }

        heroViewer.draw(arena.getHero(), gui);

        this.gui.refresh();

    }

}
