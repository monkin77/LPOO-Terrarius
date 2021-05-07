package Viewer;

import GUI.GUI;
import Model.Position;
import Model.arena.Arena;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Model.items.Item;
import Model.items.Toolbar;
import Viewer.Image.AnimatedImage;
import Viewer.Image.ImageDimensions;
import Viewer.Image.StillImage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArenaViewer {

    private final Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private final Map<Class, ElementViewer> blockCache = new HashMap<>();
    private final Map<Class, ItemViewer> itemCache = new HashMap<>();
    private final ToolbarViewer toolbarViewer = new ToolbarViewer();
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

        Toolbar toolbar = arena.getHero().getToolBar();
        this.toolbarViewer.draw(toolbar, arena.getDimensions(), gui);

        for(Integer itemKey : toolbar.getToolBar().keySet()) {
            Item item = toolbar.getItem(itemKey);
            if (!itemCache.containsKey(item.getClass())){
                itemCache.put(item.getClass(), new ItemViewer(item));
            }
            ItemViewer viewer = itemCache.get(item.getClass());

            if(itemKey == toolbar.getActiveItemIdx())
                viewer.draw(item, gui);

            viewer.drawIcon(new Position(arena.getWidth()/2 - toolbar.getDimensions().getWidth()/2 + 1 + ((itemKey-1) * (toolbar.getToolbarCellWidth()+1)), arena.getHeight() - toolbar.getDimensions().getHeight() + 1), gui);
        }

        heroViewer.draw(arena.getHero(), gui);

        this.gui.refresh();

    }

}
