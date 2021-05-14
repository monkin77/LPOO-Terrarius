package Terrarius.Viewer;

import Terrarius.GUI.GUI;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Viewer.Game.EnemyViewer;
import Terrarius.Viewer.Image.AnimatedImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class EnemyViewerTest {
    private EnemyViewer enemyViewer;
    private AnimatedImage image;
    private Map<Element, FrameHandler> elementFrameSpeedMap;
    private GUI gui;
    private FrameHandler frameHandler;
    private Enemy enemy;

    @BeforeEach
    public void setup() {
        frameHandler = Mockito.mock(FrameHandler.class);

        enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(enemy.getOrientation()).thenReturn(Element.Orientation.RIGHT);

        image = Mockito.mock(AnimatedImage.class);
        gui = Mockito.mock(GUI.class);
        elementFrameSpeedMap = new HashMap<>();

        enemyViewer = new EnemyViewer(enemy);
        enemyViewer.setImage(image);

        elementFrameSpeedMap.put(enemy, frameHandler);
        enemyViewer.setElementFrameSpeedMap(elementFrameSpeedMap);
    }

    @Test
    public void update() {
        enemyViewer.update();
        Mockito.verify(frameHandler, Mockito.times(1)).update();
    }

    @Test
    public void drawExistingEnemy() {
        enemyViewer.draw(enemy, gui);

        Assertions.assertEquals(1, enemyViewer.getElementFrameSpeedMap().size());
        Mockito.verify(image, Mockito.times(1))
                    .draw(enemy.getPosition(), enemy.getOrientation(), gui);
    }

    @Test
    public void drawNewEnemy() {
        FrameHandler handler2 = Mockito.mock(FrameHandler.class);
        Mockito.when(handler2.getTotalImages()).thenReturn(2);
        Mockito.when(handler2.getTotalFPI()).thenReturn(5);

        Mockito.when(image.getFrameSpeed()).thenReturn(handler2);

        Enemy enemy2 = Mockito.mock(Enemy.class);
        enemyViewer.draw(enemy2, gui);

        Assertions.assertEquals(2, enemyViewer.getElementFrameSpeedMap().size());
        Mockito.verify(image, Mockito.times(1))
                .draw(enemy2.getPosition(), enemy2.getOrientation(), gui);
    }
}
