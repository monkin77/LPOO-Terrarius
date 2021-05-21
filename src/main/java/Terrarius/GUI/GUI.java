package Terrarius.GUI;

import java.io.IOException;
import java.util.List;

public interface GUI {
    void drawCharacter(int x, int y, char c, String charColor, String bgColor);
    void drawCharacter(int x, int y, char c);
    void drawString(int x, int y, String message, String charColor, String bgColor);
    int getFontSize();
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    List<ACTION> getNextActions() throws IOException;
    // TODO: ADD KEY NUMBERS
    enum ACTION {UP, DOWN, RIGHT, LEFT, CLICK, QUIT, SELECT, SLOT0, SLOT1, SLOT2, SLOT3, SLOT4, SLOT5, SLOT6, SLOT7,
    SLOT8, SLOT9};
}
