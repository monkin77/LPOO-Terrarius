package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.Zombie;
import Terrarius.Viewer.FrameHandler;
import Terrarius.Viewer.Image.AnimatedImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class EnemyViewer extends ElementViewer{

    public EnemyViewer(Enemy enemy){

        setImage(new AnimatedImage());
        try {
            getImage().load("Images/Enemies/" + enemy.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
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

        AnimatedImage animatedImage = (AnimatedImage)getImage();

        animatedImage.setFrameSpeed(frameHandler);

        this.getImage().draw(element.getPosition(), element.getOrientation(), gui);
    }
}
