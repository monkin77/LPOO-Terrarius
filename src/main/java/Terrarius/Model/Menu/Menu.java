package Terrarius.Model.Menu;

import Terrarius.Model.MenuTemplate;

import java.util.Arrays;
import java.util.List;

public class Menu extends MenuTemplate<String> {

    public boolean isPlaySelected() {
        return getOption(this.getSelectedIndex()).equals("Play");
    }

    public boolean isQuitSelected() {
        return getOption(this.getSelectedIndex()).equals("Quit");
    }

    @Override
    protected List<String> initOptions() {
        return Arrays.asList("Play", "Quit");
    }
}
