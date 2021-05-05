package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.items.Item;
import Model.items.tools.Axe;
import Viewer.Image.Image;
import Viewer.Image.ImageDimensions;
import Viewer.Image.StillImage;


public class ItemViewer {
    protected Image image;

    public ItemViewer(Item item) {
        image = new StillImage();
        if(item.getClass().equals(Axe.class)) {
            image.load("Images/Axe.txt");
        }
    }

    public void draw(Item item, GUI gui){
        Hero hero = item.getHero();
        Position itemPos = new Position(hero.getPosition());
        itemPos.setX(itemPos.getX() + ( hero.getOrientation() == Element.Orientation.RIGHT ? 4 : -2 ) ); // CHANGE HERE AFTER REBASING TO GET DIMENSIONS (Hero Width)
        itemPos.setY(itemPos.getY() - 4);   // CHANGE HERE AFTER REBASING TO GET DIMENSIONS (TOOL HEIGHT)

        this.image.draw(itemPos, hero.getOrientation(), gui);
    }
}
