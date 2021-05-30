package com.lpoo.terrarius.viewer.skillTree;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.skillTree.SkillTree;
import org.junit.jupiter.api.Assertions;
import com.lpoo.terrarius.model.game.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class SkillTreeViewerTest {
    private GUI gui;
    private SkillTree skillTree;
    private SkillTreeViewer viewer;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);

        skillTree = Mockito.mock(SkillTree.class);
        Mockito.when(skillTree.getSelectedIndex()).thenReturn(0);
        Mockito.when(skillTree.getUsedPoints()).thenReturn(0);
        Mockito.when(skillTree.getHeroStats()).thenReturn(new HeroStats(new Level(1, 0), 100, 2, 1, 5));


        viewer = new SkillTreeViewer();
    }

    @Test
    public void drawSkillLabels() {
        Position pos = new Position(5, 5);
        viewer.drawSkillsLabels(pos, gui, skillTree);

        Mockito.verify(gui, Mockito.times(13)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void draw() throws IOException {
        viewer.draw(gui, skillTree);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();

        Mockito.verify(gui, Mockito.times(27)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        Mockito.verify(gui, Mockito.times(152)).drawCharacter(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyChar(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void shouldUpdate() throws IOException {
        Assertions.assertTrue(viewer.needsUpdate(skillTree));

        viewer.draw(gui, skillTree);

        Assertions.assertFalse(viewer.needsUpdate(skillTree));

    }
}
