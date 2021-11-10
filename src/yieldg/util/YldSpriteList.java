package yieldg.util;

import java.util.ArrayList;
import java.util.List;

import yieldg.YldSprite;

/**
 * Uma coletânia de YldSprites em formato de List
 */
public class YldSpriteList {
	
	public List<YldSprite> list;
	
	public YldSpriteList() {
		list = new ArrayList<YldSprite>();
	}
	
	public void add(YldSprite child) {
		if(list != null) {
			list.add(child);
		}
	}
	
	public YldSprite find(String name) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).name.equals(name)) {
				return list.get(i);
			}
		}
		return null;
	}
	public int quantity(String name) {
		int frames = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).name.equals(name)) {
				frames++;
			}
		}
		return frames;
	}
	
	public int size() {
		return list.size();
	}
	
}
