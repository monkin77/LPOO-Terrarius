package com.lpoo.terrarius.model.game.items;

import com.lpoo.terrarius.model.game.BlockPouch;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.utils.Dimensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        Mockito.when(hero.getOrientation()).thenReturn(Element.Orientation.RIGHT);
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(8, 4));

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

    @Test
    public void getPosition() {
        Position pos = blockPlacer.getPosition(new Position(5, 5));
        Assertions.assertTrue(pos.equals(new Position(9, 4)));

        Mockito.when(hero.getOrientation()).thenReturn(Element.Orientation.LEFT);
        pos = blockPlacer.getPosition(new Position(5, 5));
        Assertions.assertTrue(pos.equals(new Position(2, 4)));
    }
}
