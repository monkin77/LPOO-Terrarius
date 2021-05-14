package Terrarius.Viewer;

import Terrarius.GUI.GUI;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.items.Item;
import Terrarius.Model.items.tools.Axe;
import Terrarius.Viewer.Image.ColoredImage;
import Terrarius.Viewer.Image.Image;
import Terrarius.Viewer.Image.StillImage;

public class ItemViewer {
    private Image image;
    private Image icon;

    public ItemViewer(Item item) {
        image = new ColoredImage();
        icon = new ColoredImage();
        if(item.getClass().equals(Axe.class)) {
            image.load("Images/Items/Axe.txt");
            icon.load("Images/Items/AxeIcon.txt");
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void draw(Item item, GUI gui){
        Hero hero = item.getHero();
        Position itemPos = new Position(hero.getPosition());
        itemPos.setX(itemPos.getX() + ( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() : -item.getDimensions().getWidth() ) );
        itemPos.setY(itemPos.getY() - item.getDimensions().getHeight());

        this.image.draw(itemPos, hero.getOrientation(), gui);
    }

    public void drawIcon(Position position, GUI gui){
        this.icon.draw(position, Element.Orientation.RIGHT, gui);
    }
}
