package Controller;

import java.util.*;

import static java.awt.event.KeyEvent.*;

public class KeyboardHandler {

    private final Map<Integer, Boolean> keyMap = new HashMap<>();
    private final Map<Integer, Boolean> ignoreMap = new HashMap<>();

    public KeyboardHandler(){ //default

        List<Integer> keyCodes = Arrays.asList(
                VK_ESCAPE,
                VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT,
                VK_A, VK_S, VK_D, VK_W,
                VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9);

        for (Integer keyCode : keyCodes){
            keyMap.put(keyCode, false);
            ignoreMap.put(keyCode, false);
        }
    }

    public void pressKey(int keyCode){
        if (keyMap.containsKey(keyCode)){
            keyMap.put(keyCode, true);
        }
    }

    public void releaseKey(int keyCode){
        if (keyMap.containsKey(keyCode)){
            keyMap.put(keyCode, false);
            ignoreMap.put(keyCode, false);
        }
    }

    public boolean isKeyPressed(int keyCode){
        if (!keyMap.containsKey(keyCode)) return false;
        return keyMap.get(keyCode);
    }

    public boolean readKey(int keyCode){ //reads the key and then blocks it
        if (!keyMap.containsKey(keyCode) || !keyMap.get(keyCode) || ignoreMap.get(keyCode)) return false;
        ignoreMap.put(keyCode, true);
        return true;
    }

    public Map<Integer, Boolean> getKeys(){
        return keyMap;
    }
}
