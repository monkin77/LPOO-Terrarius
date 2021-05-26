package Terrarius.Model.Menu;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> options;
    private int selectOption;

    public Menu() {
        this.options = Arrays.asList("Play", "Quit");
        selectOption = 0;
    }

    public void nextOption() {
        selectOption = (selectOption + 1) % this.options.size();
    }

    public void previousOption() {
        selectOption--;
        if (selectOption < 0)
            selectOption = this.options.size() - 1;
    }

    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isPlaySelected() {
        return getOption(selectOption).equals("Play");
    }

    public boolean isQuitSelected() {
        return getOption(selectOption).equals("Quit");
    }

    public int getNumOptions() {
        return this.options.size();
    }

    public boolean isSelected(int i) {
        return i == selectOption;
    }

    public int getSelectedIndex() {
        return selectOption;
    }
}
