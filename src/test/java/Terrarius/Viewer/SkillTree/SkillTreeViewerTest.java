package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
<<<<<<< HEAD
import Terrarius.GUI.LanternaGui;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Utils.Dimensions;
import Terrarius.Viewer.Image.ColoredImage;
import org.junit.jupiter.api.Assertions;
=======
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.SkillTree;
>>>>>>> master
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

<<<<<<< HEAD
class SkillTreeViewerTest {
    private SkillTreeViewer skillTreeViewer;
    private GUI gui;
    private SkillTree skillTree;
    private ColoredImage image;

    @BeforeEach
    public void setup() {
        this.skillTreeViewer = new SkillTreeViewer();

        this.gui = Mockito.mock(LanternaGui.class);
        this.skillTree = Mockito.mock(SkillTree.class);
        this.image = Mockito.spy(this.skillTreeViewer.getImage());
        this.skillTreeViewer.setImage(this.image);

        Mockito.when(skillTree.getHeroStats()).thenReturn(new HeroStats());
        Mockito.when(skillTree.getSelectedIndex()).thenReturn(1);
        Mockito.when(skillTree.getUsedPoints()).thenReturn(5);
    }

    @Test
    public void validateImage() {
        Assertions.assertEquals(new Dimensions(50, 50), this.skillTreeViewer.getImage().getDimensions());
    }

    @Test
    public void draw() throws IOException {
        this.skillTreeViewer.draw(gui, skillTree);

        Position treePos = new Position(SkillTreeViewerConstants.START_POS_X, SkillTreeViewerConstants.START_POS_Y);
        Mockito.verify(this.image, Mockito.times(1)).draw(treePos, Element.Orientation.RIGHT, gui);
    }

    @Test
    public void shouldUpdate() throws IOException {
        Assertions.assertTrue(skillTreeViewer.needsUpdate(skillTree));

        skillTreeViewer.draw(gui, skillTree);

        Assertions.assertFalse(skillTreeViewer.needsUpdate(skillTree));

    }
}
=======
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
}
>>>>>>> master
