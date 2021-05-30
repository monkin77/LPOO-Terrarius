package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.elements.enemies.Enemy;
import com.lpoo.terrarius.model.game.elements.enemies.EnemyStats;
import com.lpoo.terrarius.utils.Dimensions;
import com.lpoo.terrarius.viewer.FrameHandler;
import com.lpoo.terrarius.viewer.image.AnimatedImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
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
    public void setup() throws FileNotFoundException, URISyntaxException {
        frameHandler = Mockito.mock(FrameHandler.class);

        enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(enemy.getDimensions()).thenReturn(new Dimensions(5, 5));
        Mockito.when(enemy.getOrientation()).thenReturn(Element.Orientation.RIGHT);
        Mockito.when(enemy.getComponentName()).thenReturn("Zombie");
        Mockito.when(enemy.getStats()).thenReturn(new EnemyStats(20, 2, 5, new Level(3, 0)));

        image = Mockito.mock(AnimatedImage.class);
        Mockito.doNothing().when(image).load(Mockito.anyString());

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
        Mockito.when(enemy2.getStats()).thenReturn(new EnemyStats(20, 2, 5, new Level(3, 0)));
        Mockito.when(enemy2.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(enemy2.getDimensions()).thenReturn(new Dimensions(5, 5));
        Mockito.when(enemy2.getOrientation()).thenReturn(Element.Orientation.RIGHT);
        enemyViewer.draw(enemy2, gui);

        Assertions.assertEquals(2, enemyViewer.getElementFrameSpeedMap().size());
        Mockito.verify(image, Mockito.times(1))
                .draw(enemy2.getPosition(), enemy2.getOrientation(), gui);
    }
}
