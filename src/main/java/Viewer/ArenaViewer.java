package Viewer;

import GUI.GUI;
import Model.arena.Arena;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArenaViewer {

    private final Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private final Map<Class, ElementViewer> blockCache = new HashMap<>();
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

        heroViewer.draw(arena.getHero(), gui);

        this.gui.refresh();

    }

}
