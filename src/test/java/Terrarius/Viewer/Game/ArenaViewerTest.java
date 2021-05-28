package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.blocks.DirtBlock;
import Terrarius.Model.Game.elements.blocks.StoneBlock;
import Terrarius.Model.Game.elements.blocks.WoodBlock;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.Zombie;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Game.items.tools.Pickaxe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Terrarius.Viewer.Game.GameViewerConstants.SKY_COLOR;

public class ArenaViewerTest {
    private ArenaViewer arenaViewer;
    private Map<Class, ElementViewer> enemyCache = new HashMap<>();
    private Map<Class, ElementViewer> blockCache = new HashMap<>();
    private Map<String, ItemViewer> itemCache = new HashMap<>();
    private ToolbarViewer toolbarViewer = new ToolbarViewer();
    private HeroViewer heroViewer = new HeroViewer();

    private Hero hero;
    private Arena arena;
    private GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        arenaViewer = new ArenaViewer();

        Enemy enemy = Mockito.mock(Zombie.class);
        EnemyViewer enemyViewer = Mockito.mock(EnemyViewer.class);
        enemyCache.put(enemy.getClass(), enemyViewer);
        arenaViewer.setEnemyCache(enemyCache);

        Block block1 = Mockito.mock(WoodBlock.class);
        Block block2 = Mockito.mock(StoneBlock.class);
        BlockViewer blockViewer1 = Mockito.mock(BlockViewer.class);
        BlockViewer blockViewer2 = Mockito.mock(BlockViewer.class);
        blockCache.put(block1.getClass(), blockViewer1);
        blockCache.put(block2.getClass(), blockViewer2);
        arenaViewer.setBlockCache(blockCache);

        Item item = Mockito.mock(Axe.class);
        ItemViewer itemViewer = Mockito.mock(ItemViewer.class);
        itemCache.put(item.getComponentName(), itemViewer);
        arenaViewer.setItemCache(itemCache);

        toolbarViewer = Mockito.mock(ToolbarViewer.class);
        arenaViewer.setToolbarViewer(toolbarViewer);

        heroViewer = Mockito.mock(HeroViewer.class);
        arenaViewer.setHeroViewer(heroViewer);

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(5, 0), 100, 5, 1, 10));

        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(arena.getHeight()).thenReturn(10);
        Mockito.when(arena.getWidth()).thenReturn(10);
        Mockito.when(arena.getDimensions()).thenReturn(new Dimensions(10, 10));
    }

    @Test
    public void update() {
        arenaViewer.update();

        for (ElementViewer elementViewer : enemyCache.values())
            Mockito.verify(elementViewer, Mockito.times(1)).update();

        for (ElementViewer elementViewer : blockCache.values())
            Mockito.verify(elementViewer, Mockito.times(1)).update();

        Mockito.verify(heroViewer, Mockito.times(1)).update();
    }

    @Test
    public void draw() throws IOException {
        arenaViewer = Mockito.spy(arenaViewer);
        Mockito.doNothing().when(arenaViewer).drawBackground(gui, arena);
        Mockito.doNothing().when(arenaViewer).drawBlocks(gui, arena);
        Mockito.doNothing().when(arenaViewer).drawEnemies(gui, arena);
        Mockito.doNothing().when(arenaViewer).drawToolbar(gui, arena);

        arenaViewer.draw(gui, arena);

        Mockito.verify(arenaViewer, Mockito.times(1)).drawBackground(gui, arena);
        Mockito.verify(arenaViewer, Mockito.times(1)).drawBlocks(gui, arena);
        Mockito.verify(arenaViewer, Mockito.times(1)).drawEnemies(gui, arena);
        Mockito.verify(arenaViewer, Mockito.times(1)).drawToolbar(gui, arena);

        Mockito.verify(heroViewer, Mockito.times(1)).draw(hero, gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }

    @Test
    public void drawBackground() {
        arenaViewer.drawBackground(gui, arena);
        Mockito.verify(gui, Mockito.times(100))
                .drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.eq(' '),
                            Mockito.eq(SKY_COLOR), Mockito.eq(SKY_COLOR));
    }

    @Test
    public void drawBlocks() {
        Block wood = new WoodBlock(new Position(5, 5));
        List<Block> blocks = Arrays.asList(wood, new DirtBlock(new Position(1, 1)));
        Mockito.when(arena.getBlocks()).thenReturn(blocks);

        Assertions.assertEquals(2, blockCache.size());
        arenaViewer.drawBlocks(gui, arena);
        Assertions.assertEquals(3, blockCache.size());
        Mockito.verify(blockCache.get(WoodBlock.class), Mockito.times(1)).draw(wood, gui);
    }

    @Test
    public void drawToolBar() {
        Toolbar toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(hero.getToolBar()).thenReturn(toolbar);

        arenaViewer = Mockito.spy(arenaViewer);
        arenaViewer.drawToolbar(gui, arena);

        Dimensions tbvDimension = new Dimensions(arena.getHeight() + 3, arena.getWidth());
        Mockito.verify(toolbarViewer, Mockito.times(1)).draw(Mockito.eq(toolbar),
                Mockito.argThat(dim -> dim.equals(tbvDimension)), Mockito.eq(gui));
        Mockito.verify(arenaViewer, Mockito.times(1)).drawToolbarItems(gui, toolbar, arena);
    }

    @Test
    public void drawToolbarItems() {
        Toolbar toolbar = Mockito.mock(Toolbar.class);

        Map<Integer, Item> toolbarMap = new HashMap<>();

        Item axe = Mockito.mock(Axe.class);
        Item pickaxe = Mockito.mock(Pickaxe.class);
        Mockito.when(axe.getHero()).thenReturn(hero);

        toolbarMap.put(1, axe);
        toolbarMap.put(2, pickaxe);

        Mockito.when(toolbar.getToolBar()).thenReturn(toolbarMap);
        Mockito.when(toolbar.getItem(1)).thenReturn(axe);
        Mockito.when(toolbar.getItem(2)).thenReturn(pickaxe);
        Mockito.when(toolbar.getActiveItemIdx()).thenReturn(1);
        Mockito.when(toolbar.getDimensions()).thenReturn(new Dimensions(5, 5));
        Mockito.when(toolbar.getToolbarSeparatorWidth()).thenReturn(1);

        Assertions.assertEquals(1, itemCache.size());
        arenaViewer.drawToolbarItems(gui, toolbar, arena);
        Assertions.assertEquals(2, itemCache.size());

        ItemViewer viewer = itemCache.get(Axe.class);
        Mockito.verify(viewer, Mockito.times(1)).draw(axe, gui);
        Mockito.verify(viewer, Mockito.times(1)).drawIcon(Mockito.any(), Mockito.eq(gui));
    }

    @Test
    public void drawEnemies() {
        Enemy zombie = Mockito.mock(Zombie.class);
        List<Enemy> enemies = Arrays.asList(zombie);
        Mockito.when(arena.getEnemies()).thenReturn(enemies);

        ElementViewer viewer = enemyCache.get(zombie.getClass());

        Assertions.assertEquals(1, enemyCache.size());
        arenaViewer.drawEnemies(gui, arena);
        Assertions.assertEquals(1, enemyCache.size());

        Mockito.verify(viewer, Mockito.times(1)).draw(zombie, gui);
    }
}
