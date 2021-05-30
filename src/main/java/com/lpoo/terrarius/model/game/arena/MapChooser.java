package com.lpoo.terrarius.model.game.arena;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class MapChooser {

    private final Map<Integer, Integer> levelMap;

    public MapChooser(){
        this.levelMap = new HashMap<>();
        URL resource = MapChooser.class.getResource("/Maps/MapChoices.txt");

        try {
            File file = new File(resource.toURI());
            Scanner fileScanner = new Scanner(file);

            int noLevels = fileScanner.nextInt();

            for (int i = 0; i < noLevels; i++){

                int level = fileScanner.nextInt();
                int noMaps = fileScanner.nextInt();

                levelMap.put(level, noMaps);
            }

        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MapZone getMap(int heroLevel){

        List<Integer> possibleChoices = new ArrayList<>();

        for (Integer level1 : levelMap.keySet()){
            if (heroLevel >= level1) possibleChoices.add(level1);
        }

        int level = possibleChoices.get(new Random().nextInt(possibleChoices.size()));

        int subLevel = 1 + new Random().nextInt(levelMap.get(level));

        MapBuilder mapBuilder = new LoaderMapBuilder(level, subLevel);
        return mapBuilder.createMap();
    }
}
