package Terrarius.Controller;

import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class KeyboardHandler {

    private final Map<Integer, Boolean> keyMap = new HashMap<>();

    public KeyboardHandler(){ //default
        keyMap.put(VK_ESCAPE, false);
        keyMap.put(VK_UP, false);
        keyMap.put(VK_DOWN, false);
        keyMap.put(VK_LEFT, false);
        keyMap.put(VK_RIGHT, false);
        keyMap.put(VK_0, false);
        keyMap.put(VK_1, false);
        keyMap.put(VK_2, false);
        keyMap.put(VK_3, false);
        keyMap.put(VK_4, false);
        keyMap.put(VK_5, false);
        keyMap.put(VK_6, false);
        keyMap.put(VK_7, false);
        keyMap.put(VK_8, false);
        keyMap.put(VK_9, false);
        keyMap.put(VK_ENTER, false);
    }

    public void pressKey(int keyCode){
        if (keyMap.containsKey(keyCode)){
            keyMap.put(keyCode, true);
        }
    }

    public void releaseKey(int keyCode){
        if (keyMap.containsKey(keyCode)){
            keyMap.put(keyCode, false);
        }
    }

    public boolean isKeyPressed(int keyCode){
        if (!keyMap.containsKey(keyCode)) return false;
        return keyMap.get(keyCode);
    }

    public Map<Integer, Boolean> getKeys(){
        return keyMap;
    }
}
