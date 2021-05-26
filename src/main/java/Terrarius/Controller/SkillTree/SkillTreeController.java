package Terrarius.Controller.SkillTree;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.arena.LoaderArenaBuilder;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.States.GameState;
import Terrarius.Terrarius;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SkillTreeController extends Controller<SkillTree> {
    private List<GUI.ACTION> actions;

    public SkillTreeController(SkillTree model) {
        super(model);
        this.actions = new ArrayList<>();
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        this.actions = gui.getNextActions();
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for(GUI.ACTION action : this.actions) {
            switch (action) {
                case SKILL_TREE:
                    System.out.println("Going to game state...");
                    terrarius.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
                    break;
                case SLOT0:
                    getModel().setSelected(getModel().getSelected()+1);
                    break;
            }
        }

        this.actions.clear();
    }
}
