package GUI;

import java.io.IOException;

public interface GUI {
    void drawCharacter(int x, int y, char c);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}
