package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.GUI.LanternaGui;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Utils.Dimensions;
import Terrarius.Viewer.Image.ColoredImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

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
}