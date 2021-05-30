package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Block;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArenaTest {
    Arena arena;
    Hero hero;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {

        arena = Mockito.mock(Arena.class);

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(8, 4));

        Mockito.when(arena.getHero()).thenReturn(hero);
        //Mockito.when(arena)

    }

    @Test
    public void boundaries(){



        Mockito.when(hero.getPosition()).thenReturn(new Position(-1, 1));

    }

}
