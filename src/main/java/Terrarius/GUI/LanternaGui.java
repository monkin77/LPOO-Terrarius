package Terrarius.GUI;

import Terrarius.Controller.KeyboardHandler;
import Terrarius.Controller.MouseHandler;
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

import static Terrarius.Viewer.Game.GameViewerConstants.DEFAULT_BACKGROUND_COLOR;
import static Terrarius.Viewer.Game.GameViewerConstants.DEFAULT_FOREGROUND_COLOR;
import static java.awt.event.KeyEvent.*;

public class LanternaGui implements GUI {
    private final TerminalScreen screen;
    private final int FONT_SIZE = 10;
    private TextGraphics graphics;
    private KeyboardHandler keyboardHandler;
    private MouseHandler mouseHandler;

    public LanternaGui(TerminalScreen screen, KeyboardHandler keyboardHandler, MouseHandler mouseHandler) {
        this.screen = screen;
        this.keyboardHandler = keyboardHandler;
        this.mouseHandler = mouseHandler;
        graphics = screen.newTextGraphics();
    }

    public LanternaGui(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        addMouseMotionListener(terminal);
        addMouseListener(terminal);
        addKeyListener(terminal);

        keyboardHandler = new KeyboardHandler();
        mouseHandler = new MouseHandler();
        screen = createScreen(terminal);
        graphics = screen.newTextGraphics();


    }

    private void addMouseListener(Terminal terminal) {
        ((AWTTerminalFrame) terminal).getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseHandler.mousePressed();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseHandler.mouseRelease();
            }
        });
    }

    private void addMouseMotionListener(Terminal terminal) {
        ((AWTTerminalFrame) terminal).getComponent(0).addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseHandler.setX(e.getX());
                mouseHandler.setY(e.getY());
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseHandler.setX(e.getX());
                mouseHandler.setY(e.getY());
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

        Font loadedFont = font.deriveFont(Font.PLAIN, FONT_SIZE);
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
    public void drawString(int x, int y, String message, String charColor, String bgColor) {
        graphics.setForegroundColor(TextColor.Factory.fromString(charColor));
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.putString(x, y, message);
    }

    @Override
    public int getFontSize() {
        return FONT_SIZE;
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

    @Override
    public int getWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public int getHeight() {
        return screen.getTerminalSize().getRows();
    }

    public int getMouseX(){
        return mouseHandler.getX();
    }

    public int getMouseY(){
        return mouseHandler.getY();
    }

    public void forceReleaseKey(int key) {
        keyboardHandler.releaseKey(key);
    }

    public List<ACTION> getNextActions() {

        List<ACTION> actionList = new ArrayList<>();

        if (mouseHandler.isPressed()) actionList.add(ACTION.PRESS);
        if (mouseHandler.isClick()) actionList.add(ACTION.CLICK);
        if (keyboardHandler.isKeyPressed(VK_ESCAPE)) actionList.add(ACTION.QUIT);
        if (keyboardHandler.isKeyPressed(VK_UP) || keyboardHandler.isKeyPressed(VK_W)) actionList.add(ACTION.UP);
        if (keyboardHandler.isKeyPressed(VK_DOWN) || keyboardHandler.isKeyPressed(VK_S)) actionList.add(ACTION.DOWN);
        if (keyboardHandler.isKeyPressed(VK_LEFT) || keyboardHandler.isKeyPressed(VK_A)) actionList.add(ACTION.LEFT);
        if (keyboardHandler.isKeyPressed(VK_RIGHT) || keyboardHandler.isKeyPressed(VK_D)) actionList.add(ACTION.RIGHT);
        if (keyboardHandler.readKey(VK_UP)) actionList.add(ACTION.UP_MENU);
        if (keyboardHandler.readKey(VK_DOWN))
            actionList.add(ACTION.DOWN_MENU);
        if (keyboardHandler.readKey(VK_0)) actionList.add(ACTION.SLOT0);
        if (keyboardHandler.readKey(VK_1)) actionList.add(ACTION.SLOT1);
        if (keyboardHandler.readKey(VK_2)) actionList.add(ACTION.SLOT2);
        if (keyboardHandler.readKey(VK_4)) actionList.add(ACTION.SLOT4);
        if (keyboardHandler.readKey(VK_3)) actionList.add(ACTION.SLOT3);
        if (keyboardHandler.readKey(VK_5)) actionList.add(ACTION.SLOT5);
        if (keyboardHandler.readKey(VK_6)) actionList.add(ACTION.SLOT6);
        if (keyboardHandler.readKey(VK_7)) actionList.add(ACTION.SLOT7);
        if (keyboardHandler.readKey(VK_8)) actionList.add(ACTION.SLOT8);
        if (keyboardHandler.readKey(VK_9)) actionList.add(ACTION.SLOT9);
        if (keyboardHandler.readKey(VK_ENTER)) actionList.add(ACTION.SELECT);
        if (keyboardHandler.readKey(VK_ESCAPE)) actionList.add(ACTION.QUIT);
        if (keyboardHandler.isKeyPressed(VK_ESCAPE)) actionList.add(ACTION.QUIT);
        if (keyboardHandler.readKey(VK_TAB)) actionList.add(ACTION.SKILL_TREE);
        if (keyboardHandler.readKey(VK_LEFT)) actionList.add(ACTION.ST_LEFT);
        if (keyboardHandler.readKey(VK_RIGHT)) actionList.add(ACTION.ST_RIGHT);


        return actionList;
    }

    public void forceKeysRelease() {
        keyboardHandler.releaseKey(VK_TAB);
    }
}
