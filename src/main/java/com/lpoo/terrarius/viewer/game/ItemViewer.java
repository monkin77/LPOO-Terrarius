package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.viewer.image.ColoredImage;
import com.lpoo.terrarius.viewer.image.Image;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ItemViewer {
    private Image image;
    private Image icon;

    public ItemViewer(Item item) {
        image = new ColoredImage();
        icon = new ColoredImage();
        try {
            image.load("Images/Items/" + item.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            image = null;
        }

        try {
            icon.load("Images/Items/" + item.getComponentName() + "Icon.txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            icon = null;
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void draw(Item item, GUI gui){
        if (image == null) return;
        Position itemPos = item.getPosition(item.getHero().getPosition());
        this.image.draw(itemPos, item.getHero().getOrientation(), gui);
    }

    public void drawIcon(Position position, GUI gui){
        if (icon == null) return;
        this.icon.draw(position, Element.Orientation.RIGHT, gui);
    }
}
