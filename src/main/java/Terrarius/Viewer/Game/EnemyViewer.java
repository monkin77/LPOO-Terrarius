package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Viewer.FrameHandler;
import Terrarius.Viewer.Image.AnimatedImage;
import static Terrarius.Viewer.Game.GameViewerConstants.*;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class EnemyViewer implements ElementViewer<Enemy> {
    private final AnimatedImage image;
    private Map<Element, FrameHandler> elementFrameSpeedMap;

    public EnemyViewer(Enemy enemy){
        setElementFrameSpeedMap(new HashMap<>());
        image = new AnimatedImage();

        try {
            image.load("Images/Enemies/" + enemy.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }

        FrameHandler frameHandler = image.getFrameSpeed();

        this.elementFrameSpeedMap.put(enemy, new FrameHandler(
                (int) (Math.random() * frameHandler.getCurrentImage()),
                frameHandler.getTotalImages(),
                (int) (Math.random() * frameHandler.getCurrentFPI()),
                frameHandler.getTotalFPI()));
    }

    public void update() {
        for (FrameHandler frameHandler : this.elementFrameSpeedMap.values()){
            frameHandler.update();
        }
    }

    @Override
    public void draw(Enemy enemy, GUI gui) {

        if (!elementFrameSpeedMap.containsKey(enemy)){

            FrameHandler frameHandler = image.getFrameSpeed();

            elementFrameSpeedMap.put(enemy, new FrameHandler(
                    (int) (Math.random() * frameHandler.getTotalImages()),
                    frameHandler.getTotalImages(),
                    (int) (Math.random() * frameHandler.getTotalFPI()),
                    frameHandler.getTotalFPI()));
        }

        FrameHandler frameHandler = elementFrameSpeedMap.get(enemy);

        image.setFrameSpeed(frameHandler);

        image.draw(enemy.getPosition(), enemy.getOrientation(), gui);

        String hpDisplay = ((Integer) enemy.getStats().getHp()).toString();

        gui.drawString(enemy.getPosition().getX() + enemy.getDimensions().getWidth()/2 - hpDisplay.length()/2,
                enemy.getPosition().getY() - 2, hpDisplay, HEALTH_FOREGROUND_COLOR, HEALTH_BACKGROUND_COLOR);
    }

    protected void setElementFrameSpeedMap(Map<Element, FrameHandler> elementFrameSpeedMap) {
        this.elementFrameSpeedMap = elementFrameSpeedMap;
    }
}
