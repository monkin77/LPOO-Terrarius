package GUI;

import java.io.IOException;

public interface GUI {
    void drawCharacter(int x, int y, char c, String color);
    void drawCharacter(int x, int y, char c);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    ACTION getNextAction() throws IOException;
    enum ACTION {UP, DOWN, RIGHT, LEFT, CLICK, NONE, QUIT}
}
