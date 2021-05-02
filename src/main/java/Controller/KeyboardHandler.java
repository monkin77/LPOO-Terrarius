package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
