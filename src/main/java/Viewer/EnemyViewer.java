package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;
import Viewer.Image.AnimatedImage;

public class EnemyViewer extends ElementViewer{

    public EnemyViewer(Enemy enemy){

        image = new AnimatedImage();

        if(enemy.getClass().equals(Zombie.class)){
            image.load("Images/Enemies/Zombie.txt");
        }

        FrameHandler frameHandler = ((AnimatedImage)image).getFrameSpeed();

        this.elementFrameSpeedMap.put(enemy, new FrameHandler(
                (int) (Math.random() * frameHandler.getCurrentImage()),
                frameHandler.getTotalImages(),
                (int) (Math.random() * frameHandler.getCurrentFPI()),
                frameHandler.getTotalFPI()));
    }

    @Override
    public void update() {
        for (FrameHandler frameHandler : this.elementFrameSpeedMap.values()){
            frameHandler.update();
        }
    }

    @Override
    public void draw(Element element, GUI gui) {

        if (!elementFrameSpeedMap.containsKey(element)){

            FrameHandler frameHandler = ((AnimatedImage)image).getFrameSpeed();

            elementFrameSpeedMap.put(element, new FrameHandler(
                    (int) (Math.random() * frameHandler.getTotalImages()),
                    frameHandler.getTotalImages(),
                    (int) (Math.random() * frameHandler.getTotalFPI()),
                    frameHandler.getTotalFPI()));
        }

        FrameHandler frameHandler = elementFrameSpeedMap.get(element);

        AnimatedImage animatedImage =  (AnimatedImage)image;

        animatedImage.setFrameSpeed(frameHandler);

        this.image.draw(element.getPosition(), element.getOrientation(), gui);
    }
}
