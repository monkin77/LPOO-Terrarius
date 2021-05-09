package Viewer;

import GUI.GUI;
import Model.Position;
import Model.arena.Arena;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Model.items.Item;
import Model.items.Toolbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Viewer.ViewerConstants.SKY_COLOR;


public class ArenaViewer {

    private Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private Map<Class, ElementViewer> blockCache = new HashMap<>();
    private Map<Class, ItemViewer> itemCache = new HashMap<>();
    private ToolbarViewer toolbarViewer = new ToolbarViewer();
    private HeroViewer heroViewer = new HeroViewer();

    private final GUI gui;

    public ArenaViewer(GUI gui) {
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

    public void draw(Arena arena) throws IOException {
        this.gui.clear();

        drawBackground(arena);
        drawBlocks(arena);
        drawEnemies(arena);
        drawToolbar(arena);
        heroViewer.draw(arena.getHero(), gui);

        this.gui.refresh();
    }

    protected void drawBackground(Arena arena) {
        for (int i = 0; i < arena.getWidth(); i++)
            for (int j = 0; j < arena.getHeight(); j++)
                gui.drawCharacter(i, j, ' ', SKY_COLOR, SKY_COLOR);
    }

    protected void drawBlocks(Arena arena) {
        for(Block block : arena.getBlocks()) {

            if (!blockCache.containsKey(block.getClass()))
                blockCache.put(block.getClass(), new BlockViewer(block));

            ElementViewer viewer = blockCache.get(block.getClass());
            viewer.draw(block, gui);
        }
    }

    protected void drawEnemies(Arena arena) {
        for(Enemy enemy : arena.getEnemies()) {

            if (!enemyCache.containsKey(enemy.getClass()))
                enemyCache.put(enemy.getClass(), new EnemyViewer(enemy));

            ElementViewer viewer = enemyCache.get(enemy.getClass());
            viewer.draw(enemy, gui);
        }
    }

    protected void drawToolbar(Arena arena) {
        Toolbar toolbar = arena.getHero().getToolBar();
        toolbarViewer.draw(toolbar, arena.getDimensions(), gui);
        drawToolbarItems(toolbar, arena);
    }

    protected void drawToolbarItems(Toolbar toolbar, Arena arena) {
        for(Integer itemKey : toolbar.getToolBar().keySet()) {
            Item item = toolbar.getItem(itemKey);

            if (!itemCache.containsKey(item.getClass()))
                itemCache.put(item.getClass(), new ItemViewer(item));

            ItemViewer viewer = itemCache.get(item.getClass());

            if (itemKey.equals(toolbar.getActiveItemIdx()))
                viewer.draw(item, gui);

            int toolbarSeparatorsWidth = 1;
            int toolbarStartingPositionWidth = arena.getWidth()/2 - toolbar.getDimensions().getWidth()/2 +
                    toolbarSeparatorsWidth;

            int toolbarOffsetWidth = (itemKey-1) * (toolbar.getToolbarCellWidth() + toolbarSeparatorsWidth);

            int iconX = toolbarStartingPositionWidth + toolbarOffsetWidth;
            int iconY = arena.getHeight() - toolbar.getDimensions().getHeight() + toolbarSeparatorsWidth;

            viewer.drawIcon(new Position(iconX, iconY), gui);
        }
    }

    protected void setEnemyCache(Map<Class, ElementViewer> enemyCache) {
        this.enemyCache = enemyCache;
    }

    protected void setBlockCache(Map<Class, ElementViewer> blockCache) {
        this.blockCache = blockCache;
    }

    protected void setItemCache(Map<Class, ItemViewer> itemCache) {
        this.itemCache = itemCache;
    }

    protected void setToolbarViewer(ToolbarViewer toolbarViewer) {
        this.toolbarViewer = toolbarViewer;
    }

    protected void setHeroViewer(HeroViewer heroViewer) {
        this.heroViewer = heroViewer;
    }
}
