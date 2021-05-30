package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Utils.Dimensions;
import Terrarius.Viewer.Viewer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Terrarius.Utils.GameConstants.SKY_COLOR;


public class ArenaViewer extends Viewer<Arena> {

    private Map<String, EnemyViewer> enemyCache = new HashMap<>();
    private Map<String, BlockViewer> blockCache = new HashMap<>();
    private Map<String, ItemViewer> itemCache = new HashMap<>();
    private ToolbarViewer toolbarViewer = new ToolbarViewer();
    private final StatusBarViewer statusBarViewer = new StatusBarViewer();
    private HeroViewer heroViewer = new HeroViewer();

    public void update() {
        for (EnemyViewer enemyViewer : enemyCache.values()){
            enemyViewer.update();
        }

        heroViewer.update();
    }

    @Override
    public void draw(GUI gui, Arena arena) throws IOException {
        gui.clear();

        drawBackground(gui, arena);
        drawBlocks(gui, arena);
        drawEnemies(gui, arena);
        drawToolbar(gui, arena);
        drawStatusBar(gui, arena);
        heroViewer.draw(arena.getHero(), gui);

        gui.refresh();
    }

    protected void drawBackground(GUI gui, Arena arena) {
        for (int i = 0; i < arena.getWidth(); i++)
            for (int j = 0; j < arena.getHeight(); j++)
                gui.drawCharacter(i, j, ' ', SKY_COLOR, SKY_COLOR);
    }

    protected void drawBlocks(GUI gui, Arena arena) {
        for(Block block : arena.getBlocks()) {

            if (!blockCache.containsKey(block.getComponentName()))
                blockCache.put(block.getComponentName(), new BlockViewer(block));

            BlockViewer viewer = blockCache.get(block.getComponentName());
            viewer.draw(block, gui);
        }
    }

    protected void drawEnemies(GUI gui, Arena arena) {
        for(Enemy enemy : arena.getEnemies()) {

            if (!enemyCache.containsKey(enemy.getComponentName()))
                enemyCache.put(enemy.getComponentName(), new EnemyViewer(enemy));

            EnemyViewer viewer = enemyCache.get(enemy.getComponentName());
            viewer.draw(enemy, gui);
        }
    }

    protected void drawToolbar(GUI gui, Arena arena) {
        Toolbar toolbar = arena.getHero().getToolBar();
        Dimensions tbvDimensions = new Dimensions(arena.getDimensions().getHeight() + 3,
                arena.getDimensions().getWidth());
        toolbarViewer.draw(toolbar, tbvDimensions, gui);
        drawToolbarItems(gui, toolbar, arena);
    }

    protected void drawToolbarItems(GUI gui, Toolbar toolbar, Arena arena) {
        for(Integer itemKey : toolbar.getToolBar().keySet()) {
            Item item = toolbar.getItem(itemKey);

            if (!itemCache.containsKey(item.getComponentName()))
                itemCache.put(item.getComponentName(), new ItemViewer(item));

            ItemViewer viewer = itemCache.get(item.getComponentName());

            if (itemKey.equals(toolbar.getActiveItemIdx()))
                viewer.draw(item, gui);

            Position iconPosition = toolbarViewer.calculateIconPosition(arena, toolbar, itemKey);

            viewer.drawIcon(iconPosition, gui);
        }
    }

    protected void drawStatusBar(GUI gui, Arena arena) {
        statusBarViewer.draw(arena.getHero(), arena.getDimensions(), gui);
    }

    public void setEnemyCache(Map<String, EnemyViewer> enemyCache) {
        this.enemyCache = enemyCache;
    }

    public void setBlockCache(Map<String, BlockViewer> blockCache) {
        this.blockCache = blockCache;
    }

    public void setItemCache(Map<String, ItemViewer> itemCache) {
        this.itemCache = itemCache;
    }

    public void setToolbarViewer(ToolbarViewer toolbarViewer) {
        this.toolbarViewer = toolbarViewer;
    }

    public void setHeroViewer(HeroViewer heroViewer) {
        this.heroViewer = heroViewer;
    }
}
