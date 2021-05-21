package Model.map;

import Model.arena.LoaderArenaBuilder;
import Model.elements.Hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MapChooser {

    public Map<Integer, Integer> levelMap = new HashMap<>();

    public MapChooser(){
        URL resource = LoaderArenaBuilder.class.getResource("/Maps/MapChoices.txt");
        try {
            File file = new File(resource.toURI());
            Scanner fileScanner = new Scanner(file);

            int noLevels = fileScanner.nextInt();

            for (int i = 0; i < noLevels; i++){

                int level = fileScanner.nextInt();
                int noMaps = fileScanner.nextInt();

                levelMap.put(level, noMaps);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MapZone getMap(int heroLevel){ //Mayb
        int level = 1;

        for (Integer level1 : levelMap.keySet()){
            if (heroLevel >= level1) level = level1;
        }

        int subLevel = 1 + new Random().nextInt(levelMap.get(level));

        MapBuilder mapBuilder = new MapBuilder(level, subLevel);
        return  mapBuilder.createMap();
    }
}
