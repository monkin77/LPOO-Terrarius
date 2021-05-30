package Terrarius.States;

import Terrarius.Controller.Menu.MenuController;
import Terrarius.Model.Menu.Menu;
import Terrarius.Viewer.Menu.MenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MenuStateTest {
    private State state;
    private Menu menu;

    @BeforeEach
    public void setup() {
        this.menu = Mockito.mock(Menu.class);
        this.state = new MenuState(this.menu);
    }

    @Test
    public void menuSpecifiers() {
        Assertions.assertTrue(state.getModel() instanceof Menu);
        Assertions.assertTrue(state.getController() instanceof MenuController);
        Assertions.assertTrue(state.getViewer() instanceof MenuViewer);
    }
}