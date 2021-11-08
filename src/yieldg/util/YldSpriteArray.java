package yieldg.util;

import yieldg.YldSprite;

public class YldSpriteArray {

    public YldSprite[] list;

    public YldSpriteArray(int size) {
        list = new YldSprite[size];
    }

    public YldSprite find(String name) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].name.equals(name)) {
                return list[i];
            }
        }
        return null;
    }

    public int quantityOf(String name) {
        int frames = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i].name.equals(name)) {
                frames++;
            }
        }
        return frames;
    }

    public int size() {
        return list.length;
    }

}
