package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.items.Item;
import Model.items.tools.Axe;
import Viewer.Image.ColoredImage;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

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
        Position itemPos = item.getPosition();

        this.image.draw(itemPos, item.getHero().getOrientation(), gui);
    }

    public void drawIcon(Position position, GUI gui){
        this.icon.draw(position, Element.Orientation.RIGHT, gui);
    }
}
