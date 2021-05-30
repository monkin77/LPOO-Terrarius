package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.controller.menu.MenuController;
import com.lpoo.terrarius.model.menu.Menu;
import com.lpoo.terrarius.viewer.menu.MenuViewer;
import com.lpoo.terrarius.viewer.Viewer;

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
