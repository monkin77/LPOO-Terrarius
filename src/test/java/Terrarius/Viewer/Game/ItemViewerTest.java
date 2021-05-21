package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.items.Item;
import Terrarius.Model.items.tools.Axe;
import Terrarius.Viewer.Image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemViewerTest {
    private ItemViewer itemViewer;
    private GUI gui;
    private Image image, icon;
    private Item item;
    private Hero hero;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        image = Mockito.mock(Image.class);
        icon = Mockito.mock(Image.class);

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(5, 5));

        item = Mockito.mock(Axe.class);
        Mockito.when(item.getHero()).thenReturn(hero);
        Mockito.when(item.getDimensions()).thenReturn(new Dimensions(2, 2));

        itemViewer = new ItemViewer(item);
        itemViewer.setIcon(icon);
        itemViewer.setImage(image);
    }

    @Test
    public void drawRightOrientation() {
        Mockito.when(hero.getOrientation()).thenReturn(Element.Orientation.RIGHT);

        itemViewer.draw(item, gui);
        Mockito.verify(image, Mockito.times(1))
                .draw(Mockito.argThat((pos) -> pos.equals(new Position(15, 8))),
                        Mockito.eq(Element.Orientation.RIGHT), Mockito.eq(gui));
    }

    @Test
    public void drawLeftOrientation() {
        Mockito.when(hero.getOrientation()).thenReturn(Element.Orientation.LEFT);

        itemViewer.draw(item, gui);
        Mockito.verify(image, Mockito.times(1))
                .draw(Mockito.argThat((pos) -> pos.equals(new Position(8, 8))),
                        Mockito.eq(Element.Orientation.LEFT), Mockito.eq(gui));
    }

    @Test
    public void drawIcon() {
        Position pos = new Position(10, 10);
        itemViewer.drawIcon(pos, gui);
        Mockito.verify(icon, Mockito.times(1)).draw(pos, Element.Orientation.RIGHT, gui);
    }
}
