package Viewer;

import GUI.GUI;
import Model.Position;
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

    public void draw(Item item, ImageDimensions dimensions, GUI gui){
        StillImage currImage = (StillImage) image;
        Hero hero = item.getHero();
        Position itemPos = new Position(hero.getPosition());
        itemPos.setX(itemPos.getX() + dimensions.getWidth());
        itemPos.setY(itemPos.getY() - currImage.getDimensions().getHeight());

        this.image.draw(itemPos, gui);
    }
}
