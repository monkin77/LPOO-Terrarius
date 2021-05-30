package com.lpoo.terrarius.model.game.arena;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.model.game.elements.enemies.Enemy;
import com.lpoo.terrarius.utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

public class LoaderMapBuilder extends MapBuilder {

    private final int level;
    private final int subLevel;

    LoaderMapBuilder(int level, int subLevel){
        super();
        this.level = level;
        this.subLevel = subLevel;
    }

    @Override
    protected void createSpawns(MapZone mapZone) {
        try {
            Scanner fileScanner = getScannerFromLevelFile("LeftSpawn.txt");
            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();
            mapZone.setLeftSpawn(new Position(x, y));

            fileScanner = getScannerFromLevelFile("RightSpawn.txt");
            x = fileScanner.nextInt();
            y = fileScanner.nextInt();
            mapZone.setRightSpawn(new Position(x, y));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Block> createBlocks() {
        List<Block> blockList = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromLevelFile("Terrain.txt");
            java.util.Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            blockList = createBlockList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return blockList;
    }

    @Override
    protected List<Enemy> createEnemies(){
        List<Enemy> enemies = new ArrayList<>();

        try {
            Scanner fileScanner = getScannerFromLevelFile("Enemies.txt");
            java.util.Map<Character, String> characterClassMap = createCharacterClassMap(fileScanner);
            enemies = createEnemyList(characterClassMap, fileScanner);

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return enemies;
    }

    @Override
    protected Dimensions createDimensions() {
        int width = 0;
        int height = 0;

        try {
            URL resource = LoaderMapBuilder.class.getResource("/Maps/Dimensions.txt");
            Scanner fileScanner = new Scanner(new File(resource.toURI()));

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

        for (int j = 0; j < getDimensions().getHeight() / 4; j++) {
            String line = fileScanner.nextLine();

            for (int i = 0; i < min(getDimensions().getWidth() / 4, line.length()); i++) {
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

    private Scanner getScannerFromLevelFile(String fileName) throws URISyntaxException, FileNotFoundException {
        URL resource = LoaderMapBuilder.class.getResource("/Maps/Level_" + level + "/Map_" + subLevel + "/" + fileName);
        File file = new File(resource.toURI());
        return new Scanner(file);
    }
}
