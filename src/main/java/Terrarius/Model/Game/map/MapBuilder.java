package Terrarius.Model.Game.map;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

public class MapBuilder {

    private final int level;
    private final int subLevel;
    private final Dimensions dimensions;

    MapBuilder(int level, int subLevel){ //TODO maybe not hero just the level
        this.level = level;
        this.subLevel = subLevel;
        this.dimensions = this.createDimensions();
    }

    private Scanner getScannerFromFile(String fileName) throws URISyntaxException, FileNotFoundException {
        URL resource = MapBuilder.class.getResource("/Maps/Level_" + level + "/Map_" + subLevel + "/" + fileName);
        File file = new File(resource.toURI());
        return new Scanner(file);
    }

    private void createSpawns(MapZone mapZone) throws FileNotFoundException, URISyntaxException {
        Scanner fileScanner = getScannerFromFile("LeftSpawn.txt");
        int x = fileScanner.nextInt();
        int y = fileScanner.nextInt();
        mapZone.setLeftSpawn(new Position(x, y));

        fileScanner = getScannerFromFile("RightSpawn.txt");
        x = fileScanner.nextInt();
        y = fileScanner.nextInt();
        mapZone.setRightSpawn(new Position(x, y));
    }

    public MapZone createMap() {
        MapZone mapZone = new MapZone(dimensions.getWidth(), dimensions.getHeight());

        mapZone.setBlocks(this.createBlocks());
        mapZone.setEnemies(this.createEnemies());

        try {
            this.createSpawns(mapZone);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

        return mapZone;
    }

    public List<Block> createBlocks(){
        List<Block> blockList = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromFile("Terrain.txt");
            java.util.Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            blockList = createBlockList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return blockList;
    }

    public List<Enemy> createEnemies(){
        List<Enemy> enemies = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromFile("Enemies.txt");
            java.util.Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            enemies = createEnemyList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return enemies;
    }

    public Dimensions createDimensions(){
        int width = 0;
        int height = 0;

        try {
            Scanner fileScanner = getScannerFromFile("Dimensions.txt");

            width = fileScanner.nextInt();
            height = fileScanner.nextInt();

        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Dimensions(height, width);
    }

    private java.util.Map<Character, String> createCharacterClassMap(Scanner fileScanner) {
        java.util.Map<Character, String> characterClassMap = new HashMap<>();

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

    private List<Block> createBlockList(java.util.Map<Character, String> characterClassMap, Scanner fileScanner) {
        List<Block> blockList = new ArrayList<>();

        for (int j = 0; j < dimensions.getHeight() / 4; j++) {
            String line = fileScanner.nextLine();

            for (int i = 0; i < min(dimensions.getWidth() / 4, line.length()); i++) {
                Character key = line.charAt(i);
                String class_s;

                if (characterClassMap.containsKey(key))
                    class_s = characterClassMap.get(key);
                else
                    continue;

                try {
                    blockList.add(new Block(new Position(i * 4, j * 4), class_s));
                } catch (FileNotFoundException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        return blockList;
    }

    private List<Enemy> createEnemyList(java.util.Map<Character, String> characterClassMap, Scanner fileScanner) {
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

            try {
                enemies.add(new Enemy(new Position(x, y), new Level(level, xp_base), class_s));
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }

            fileScanner.nextLine();
        }
        return enemies;
    }
}
