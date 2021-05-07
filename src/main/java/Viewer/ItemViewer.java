package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.items.Item;
import Model.items.tools.Axe;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

//TODO: REFACTOR PROTECTED?
public class ItemViewer {
    protected Image image;
    protected Image icon;

    public ItemViewer(Item item) {
        image = new StillImage();
        icon = new StillImage();
        if(item.getClass().equals(Axe.class)) {
            image.load("Images/Items/Axe.txt");
            icon.load("Images/Items/AxeIcon.txt");
        }
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
