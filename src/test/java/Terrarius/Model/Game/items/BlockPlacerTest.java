package Terrarius.Model.Game.items;

import Terrarius.Model.Game.BlockPouch;
import Terrarius.Model.Game.elements.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BlockPlacerTest {

    private Toolbar toolbar;
    private Hero hero;
    private BlockPouch blockPouch;

    private BlockPlacer blockPlacer;

    @BeforeEach
    public void setup() throws FileNotFoundException, URISyntaxException {
        hero = Mockito.mock(Hero.class);
        toolbar = Mockito.mock(Toolbar.class);
        blockPouch = Mockito.mock(BlockPouch.class);

        Mockito.when(hero.getToolBar()).thenReturn(toolbar);
        Mockito.when(toolbar.getBlockPouch()).thenReturn(blockPouch);
        Mockito.when(blockPouch.getCurrentBlockName()).thenReturn("TestBlock");

        blockPlacer = new BlockPlacer(hero);
    }

    @Test
    public void emptyPouch(){
        Mockito.when(blockPouch.getCurrentBlockQuantity()).thenReturn(0);
        Assertions.assertEquals("BlockPlacer", blockPlacer.getComponentName());
    }

    @Test
    public void notEmptyPouch(){
        Mockito.when(blockPouch.getCurrentBlockQuantity()).thenReturn(1);
        Assertions.assertEquals("TestBlock", blockPlacer.getComponentName());
    }

}
