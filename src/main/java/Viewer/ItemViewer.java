package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.items.Item;
import Model.items.tools.Axe;
import Viewer.Image.Image;
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
        Position itemPos = new Position(item.getHero().getPosition());
        itemPos.setX(itemPos.getX() + 4);
        itemPos.setY(itemPos.getY() - 4);
        this.image.draw(itemPos, gui);   // could change Element usage to Position
    }
}
