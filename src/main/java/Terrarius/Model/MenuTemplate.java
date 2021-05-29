package Terrarius.Model;

import java.util.List;

public abstract class MenuTemplate<T> {
    private final List<T> options;
    private int selectedOption;

    public MenuTemplate() {
        this.options = initOptions();
        this.selectedOption = 0;
    }

    public void nextOption() {
        selectedOption = (selectedOption + 1) % this.options.size();
    }

    public void previousOption() {
        selectedOption--;
        if (selectedOption < 0)
            selectedOption = this.options.size() - 1;
    }

    public T getOption(int i) {
        return options.get(i);
    }

    public T getSelectedOption() {
        return getOption(this.selectedOption);
    }

    public List<T> getOptions() {
        return options;
    }

    public int getNumOptions() {
        return this.options.size();
    }

    public boolean isSelected(int i) {
        return i == selectedOption;
    }

    public int getSelectedIndex() {
        return selectedOption;
    }

    protected abstract List<T> initOptions();

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}
