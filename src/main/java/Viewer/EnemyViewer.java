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

        FrameSpeed frameSpeed = ((AnimatedImage)image).getFrameSpeed();

        this.elementFrameSpeedMap.put(enemy, new FrameSpeed(
                (int) (Math.random() * frameSpeed.getCurrentFrame()),
                frameSpeed.getTotalFrames(),
                (int) (Math.random() * frameSpeed.getCurrentSpeed()),
                frameSpeed.getTotalSpeed()));
    }

    @Override
    public void update() {
        for (FrameSpeed frameSpeed : this.elementFrameSpeedMap.values()){
            frameSpeed.update();
        }
    }

    @Override
    public void draw(Element element, GUI gui) {

        if (!elementFrameSpeedMap.containsKey(element)){

            FrameSpeed frameSpeed = ((AnimatedImage)image).getFrameSpeed();

            elementFrameSpeedMap.put(element, new FrameSpeed(
                    (int) (Math.random() * frameSpeed.getTotalFrames()),
                    frameSpeed.getTotalFrames(),
                    (int) (Math.random() * frameSpeed.getTotalSpeed()),
                    frameSpeed.getTotalSpeed()));
        }

        FrameSpeed frameSpeed = elementFrameSpeedMap.get(element);

        AnimatedImage animatedImage =  (AnimatedImage)image;

        animatedImage.setFrameSpeed(frameSpeed);

        this.image.draw(element, gui);
    }
}
