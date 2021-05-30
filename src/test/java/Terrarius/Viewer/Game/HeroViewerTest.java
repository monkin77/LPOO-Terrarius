package Terrarius.Viewer.Game;

/*
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Viewer.Image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroViewerTest {
    private HeroViewer heroViewer;
    private Hero hero;
    private Image image;
    private GUI gui;

    @BeforeEach
    public void setup() {
        image = Mockito.mock(Image.class);
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
        Mockito.verify(image, Mockito.times(1)).update();
    }

    @Test
    public void draw() {
        heroViewer.draw(hero, gui);
        Mockito.verify(image, Mockito.times(1))
                    .draw(hero.getPosition(), Element.Orientation.RIGHT, gui);
    }
}*/
