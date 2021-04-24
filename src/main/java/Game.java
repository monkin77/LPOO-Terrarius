import GUI.LanternaGui;
import Model.Position;
import Model.arena.Arena;
import Model.elements.Hero;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        LanternaGui gui = new LanternaGui(150, 100);
        System.out.println("Here we go");

        Arena arena = new Arena(150, 100);
        arena.setHero(new Hero(new Position(15, 15)));  /* Use arena builder in the future */
    }
}
