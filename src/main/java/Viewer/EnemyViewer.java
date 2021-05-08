package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;
import Viewer.Image.AnimatedImage;

public class EnemyViewer extends ElementViewer{

    public EnemyViewer(Enemy enemy){

        setImage(new AnimatedImage());

        if(enemy.getClass().equals(Zombie.class)){
            getImage().load("Images/Enemies/Zombie.txt");
        }

        FrameHandler frameHandler = ((AnimatedImage)getImage()).getFrameSpeed();

        this.getElementFrameSpeedMap().put(enemy, new FrameHandler(
                (int) (Math.random() * frameHandler.getCurrentImage()),
                frameHandler.getTotalImages(),
                (int) (Math.random() * frameHandler.getCurrentFPI()),
                frameHandler.getTotalFPI()));
    }

    @Override
    public void update() {
        for (FrameHandler frameHandler : this.getElementFrameSpeedMap().values()){
            frameHandler.update();
        }
    }

    @Override
    public void draw(Element element, GUI gui) {

        if (!getElementFrameSpeedMap().containsKey(element)){

            FrameHandler frameHandler = ((AnimatedImage)getImage()).getFrameSpeed();

            getElementFrameSpeedMap().put(element, new FrameHandler(
                    (int) (Math.random() * frameHandler.getTotalImages()),
                    frameHandler.getTotalImages(),
                    (int) (Math.random() * frameHandler.getTotalFPI()),
                    frameHandler.getTotalFPI()));
        }

        FrameHandler frameHandler = getElementFrameSpeedMap().get(element);

        AnimatedImage animatedImage =  (AnimatedImage)getImage();

        animatedImage.setFrameSpeed(frameHandler);

        this.getImage().draw(element.getPosition(), element.getOrientation(), gui);
    }
}
