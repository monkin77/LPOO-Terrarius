package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;
import Viewer.Image.AnimatedImage;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

public class EnemyViewer extends ElementViewer{

    public EnemyViewer(Enemy enemy){

        image = new AnimatedImage();

        if(enemy.getClass().equals(Zombie.class)){
            image.load("Images/Zombie.txt");
        }
    }

    @Override
    public void update() {
        image.update();
    }

    @Override
    public void draw(Element element, GUI gui) {
        this.image.draw(element, gui);
    }
}
