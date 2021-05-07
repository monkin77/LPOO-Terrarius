package Model.arena;

import Model.Level;
import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;

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
        URL resource = LoaderArenaBuilder.class.getResource("/Maps/Map" + level + "/Dimensions.txt");
        Scanner fileScanner = new Scanner(new File(resource.toURI()));

        this.width = fileScanner.nextInt();
        this.height = fileScanner.nextInt();
    }

    @Override
    protected int getHeight() {
        return this.height;
    }

    @Override
    protected int getWidth() {
        return this.width;
    }

    @Override
    protected List<Block> createBlocks() {

        Map<Character, String> characterClassMap = new HashMap<>();

        List<Block> blockList = new ArrayList<>();

        URL resource = LoaderArenaBuilder.class.getResource("/Maps/Map" + level + "/Terrain.txt");

        try {
            Scanner fileScanner = new Scanner(new File(resource.toURI()));

            int no_classes = fileScanner.nextInt();

            fileScanner.nextLine(); //clear

            for (int i = 0; i < no_classes; i++) {
                Character key = fileScanner.next().charAt(0);
                String class_s = fileScanner.next();
                fileScanner.nextLine(); //clear

                characterClassMap.put(key, class_s);


            }

            for (int j = 0; j < height / 4; j++) {

                String line = fileScanner.nextLine();

                for (int i = 0; i < min(width / 4, line.length()); i++) {

                    Character key = line.charAt(i);

                    String class_s;

                    if (characterClassMap.containsKey(key)) {
                        class_s = characterClassMap.get(key);
                    } else {
                        continue;
                    }

                    if (class_s.equals("DirtBlock")) {
                        blockList.add(new DirtBlock(new Position(i * 4, j * 4)));
                    } else if (class_s.equals("StoneBlock")) {
                        blockList.add(new StoneBlock(new Position(i * 4, j * 4)));
                    } else if (class_s.equals("WoodBlock")) {
                        blockList.add(new WoodBlock(new Position(i * 4, j * 4)));
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return blockList;
    }

    @Override
    protected List<Enemy> createEnemies() {

        Map<Character, String> characterClassMap = new HashMap<>();

        List<Enemy> enemies = new ArrayList<>();

        URL resource = LoaderArenaBuilder.class.getResource("/Maps/Map" + level + "/Enemies.txt");

        try {
            Scanner fileScanner = new Scanner(new File(resource.toURI()));

            int no_classes = fileScanner.nextInt();

            fileScanner.nextLine(); //clear

            for (int i = 0; i < no_classes; i++) {
                Character key = fileScanner.next().charAt(0);
                String class_s = fileScanner.next();
                fileScanner.nextLine(); //clear

                characterClassMap.put(key, class_s);
            }

            int no_enemies = fileScanner.nextInt();
            fileScanner.nextLine();

            for (int i = 0; i < no_enemies; i++) {

                Character key = fileScanner.next().charAt(0);

                String class_s;

                if (characterClassMap.containsKey(key)) {
                    class_s = characterClassMap.get(key);
                } else {
                    continue;
                }

                int x = fileScanner.nextInt();
                int y = fileScanner.nextInt();
                int level = fileScanner.nextInt();
                int xp_base = fileScanner.nextInt();

                if (class_s.equals("Zombie")) {
                    enemies.add(new Zombie(new Position(x, y), new Level(level, xp_base)));
                }

                fileScanner.nextLine();

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        return enemies;
    }

    @Override
    protected Hero createHero() {

        URL resource = LoaderArenaBuilder.class.getResource("/Maps/Map" + level + "/Hero.txt");

        try {
            Scanner fileScanner = new Scanner(new File(resource.toURI()));

            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();

            return new Hero(new Position(x, y));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
