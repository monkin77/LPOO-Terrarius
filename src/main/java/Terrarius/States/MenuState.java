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
    protected Viewer<Menu> createViewer() {
        return new MenuViewer();
    }

    @Override
    protected Controller<Menu> createController() {
        return new MenuController(getModel());
    }
}
