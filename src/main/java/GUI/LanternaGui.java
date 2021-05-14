package GUI;

import Controller.KeyboardHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static Viewer.ViewerConstants.DEFAULT_BACKGROUND_COLOR;
import static Viewer.ViewerConstants.DEFAULT_FOREGROUND_COLOR;
import static java.awt.event.KeyEvent.*;

public class LanternaGui implements GUI {
    private final TerminalScreen screen;
    private TextGraphics graphics;
    private boolean mouseClicked;
    private KeyboardHandler keyboardHandler;

    public LanternaGui(TerminalScreen screen, KeyboardHandler keyboardHandler) {
        this.screen = screen;
        this.keyboardHandler = keyboardHandler;
        graphics = screen.newTextGraphics();
        this.mouseClicked = true;
    }

    public LanternaGui(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        addMouseListener(terminal);
        addKeyListener(terminal);

        keyboardHandler = new KeyboardHandler();
        screen = createScreen(terminal);
        graphics = screen.newTextGraphics();
    }

    private void addMouseListener(Terminal terminal) {
        mouseClicked = false;
        ((AWTTerminalFrame) terminal).getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /* If mouse position is needed, use e.getX() and e.getY()
                   and remove setCursorPosition(null) below
                 */
                mouseClicked = true;
            }
        });
    }

    private void addKeyListener(Terminal terminal) {
        ((AWTTerminalFrame) terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyboardHandler.pressKey(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                keyboardHandler.releaseKey(e.getKeyCode());
            }
        });
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);  // maybe we'll use mouse later
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 10);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    @Override
    public void drawCharacter(int x, int y, char c, String charColor, String bgColor) {

        graphics.setForegroundColor(TextColor.Factory.fromString(charColor));
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.setCharacter(x, y, c);
    }

    @Override
    public void drawCharacter(int x, int y, char c) {
        graphics.setForegroundColor(TextColor.Factory.fromString(DEFAULT_FOREGROUND_COLOR));
        graphics.setBackgroundColor(TextColor.Factory.fromString(DEFAULT_BACKGROUND_COLOR));
        graphics.setCharacter(x, y, c);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    public List<ACTION> getNextActions() {

        List<ACTION> actionList = new ArrayList<>();

        if (mouseClicked) {
            mouseClicked = false;
            actionList.add(ACTION.CLICK);
        }

        if (keyboardHandler.isKeyPressed(VK_ESCAPE)){
            actionList.add(ACTION.QUIT);
        }
        if (keyboardHandler.isKeyPressed(VK_UP)) actionList.add(ACTION.UP);
        if (keyboardHandler.isKeyPressed(VK_DOWN)) actionList.add(ACTION.DOWN);
        if (keyboardHandler.isKeyPressed(VK_LEFT)) actionList.add(ACTION.LEFT);
        if (keyboardHandler.isKeyPressed(VK_RIGHT)) actionList.add(ACTION.RIGHT);

        return actionList;
    }
}
