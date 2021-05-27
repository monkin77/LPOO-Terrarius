package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.blocks.DirtBlock;
import Terrarius.Model.Game.elements.blocks.StoneBlock;
import Terrarius.Model.Game.elements.blocks.WoodBlock;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.Zombie;
import Terrarius.Model.Game.items.buffs.*;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Game.items.tools.Pickaxe;
import Terrarius.Model.Game.items.tools.Shovel;
import Terrarius.Model.Game.items.tools.Sword;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static java.lang.Math.min;


public class LoaderArenaBuilder extends ArenaBuilder {

    private final int level;
    private final int width;
    private final int height;

    public LoaderArenaBuilder(int level) throws FileNotFoundException, URISyntaxException {
        this.level = level;
        Scanner fileScanner = getScannerFromFile("Dimensions.txt");

        this.width = fileScanner.nextInt();
        this.height = fileScanner.nextInt();
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    protected List<Block> createBlocks() {
        List<Block> blockList = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromFile("Terrain.txt");
            Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            blockList = createBlockList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return blockList;
    }

    @Override
    protected List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromFile("Enemies.txt");
            Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            enemies = createEnemyList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return enemies;
    }

    @Override
    protected Hero createHero() {
        try {
            Scanner fileScanner = getScannerFromFile("Hero.txt");

            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();

            Hero hero = new Hero(new Position(x, y));
            hero.addItem(2, new Axe(hero));  // TODO: CHANGE WHEN WE HAVE ITEM CRAFTING
            hero.addItem(3, new Pickaxe(hero));
            hero.addItem(4, new Shovel(hero));
            hero.addItem(5, new Sword(hero));
            hero.addItem(6, new Apple(hero));
            hero.addItem(7, new BattlePotion(hero));
            hero.addItem(8, new ElasticPotion(hero));
            hero.addItem(9, new SwiftnessPotion(hero));

            return hero;

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Scanner getScannerFromFile(String fileName) throws URISyntaxException, FileNotFoundException {
        URL resource = LoaderArenaBuilder.class.getResource("/Maps/Map" + level + "/" + fileName);
        File file = new File(resource.toURI());
        return new Scanner(file);
    }

    private Map<Character, String> createCharacterClassMap(Scanner fileScanner) {
        Map<Character, String> characterClassMap = new HashMap<>();

        int no_classes = fileScanner.nextInt();
        fileScanner.nextLine(); //clear

        for (int i = 0; i < no_classes; i++) {
            Character key = fileScanner.next().charAt(0);
            String class_s = fileScanner.next();
            fileScanner.nextLine(); //clear

            characterClassMap.put(key, class_s);
        }
        return characterClassMap;
    }

    private List<Block> createBlockList(Map<Character, String> characterClassMap, Scanner fileScanner) {
        List<Block> blockList = new ArrayList<>();

        for (int j = 0; j < height / 4; j++) {
            String line = fileScanner.nextLine();

            for (int i = 0; i < min(width / 4, line.length()); i++) {
                Character key = line.charAt(i);
                String class_s;

                if (characterClassMap.containsKey(key))
                    class_s = characterClassMap.get(key);
                else
                    continue;

                if (class_s.equals("DirtBlock"))
                    blockList.add(new DirtBlock(new Position(i * 4, j * 4)));
                else if (class_s.equals("StoneBlock"))
                    blockList.add(new StoneBlock(new Position(i * 4, j * 4)));
                else if (class_s.equals("WoodBlock"))
                    blockList.add(new WoodBlock(new Position(i * 4, j * 4)));
            }
        }
        return blockList;
    }

    private List<Enemy> createEnemyList(Map<Character, String> characterClassMap, Scanner fileScanner) {
        List<Enemy> enemies = new ArrayList<>();

        int no_enemies = fileScanner.nextInt();
        fileScanner.nextLine();

        for (int i = 0; i < no_enemies; i++) {
            Character key = fileScanner.next().charAt(0);
            String class_s;

            if (characterClassMap.containsKey(key))
                class_s = characterClassMap.get(key);
            else
                continue;

            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();
            int level = fileScanner.nextInt();
            int xp_base = fileScanner.nextInt();

            if (class_s.equals("Zombie"))
                enemies.add(new Zombie(new Position(x, y), new Level(level, xp_base)));

            fileScanner.nextLine();
        }
        return enemies;
    }
}
