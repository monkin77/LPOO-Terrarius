package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.Controller.Menu.MenuController;
import Terrarius.Model.Menu.Menu;
import Terrarius.Viewer.Menu.MenuViewer;
import Terrarius.Viewer.Viewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer();
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
