package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.viewer.image.AnimatedImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroViewerTest {
    private HeroViewer heroViewer;
    private Hero hero;
    private AnimatedImage image;
    private GUI gui;

    @BeforeEach
    public void setup() {
        image = Mockito.mock(AnimatedImage.class);
        gui = Mockito.mock(GUI.class);

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(hero.getOrientation()).thenReturn(Element.Orientation.RIGHT);

        heroViewer = new HeroViewer();
        heroViewer.setImage(image);
    }

    @Test
    public void update() {
        heroViewer.update();
        Mockito.verify(heroViewer.image, Mockito.times(1)).update();
    }

    @Test
    public void draw() {
        heroViewer.draw(hero, gui);
        Mockito.verify(image, Mockito.times(1))
                    .draw(hero.getPosition(), Element.Orientation.RIGHT, gui);
    }
}
