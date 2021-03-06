package com.lpoo.terrarius.controller.game;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.arena.Arena;
import com.lpoo.terrarius.model.menu.Menu;
import com.lpoo.terrarius.states.MenuState;
import com.lpoo.terrarius.Terrarius;

import java.io.IOException;
import java.util.List;

import static com.lpoo.terrarius.utils.GameConstants.MS_PER_UPDATE;

public class GameController extends Controller<Arena> {
    private final ArenaController arenaController;

    public GameController(Arena arena, ArenaController arenaController) {
        super(arena);
        this.arenaController = arenaController;
    }

    public GameController(Arena arena) {
        super(arena);
        this.arenaController = new ArenaController(arena, new HeroController(arena), new EnemyController(arena), MS_PER_UPDATE);
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        List<GUI.ACTION> actions = gui.getNextActions();
        if (actions.contains(GUI.ACTION.QUIT))
            terrarius.setState(terrarius.createMenuState());
        else if (actions.contains(GUI.ACTION.SKILL_TREE))
            terrarius.setState(terrarius.createSkillTreeState());
        else if (actions.contains(GUI.ACTION.ITEM_SHOP))
            terrarius.setState(terrarius.createItemShopState());
        else
            arenaController.addActions(actions);
        arenaController.setHeroTargetPosition(new Position(gui.getMouseX() / gui.getFontSize(), gui.getMouseY()/ gui.getFontSize()));
    }

    @Override
    public void update(Terrarius terrarius) {
        arenaController.update();
        if (arenaController.checkEnd())
            terrarius.setState(new MenuState(new Menu()));
    }
}
