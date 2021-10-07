package yieldg.util;

import java.util.ArrayList;
import java.util.List;

import yieldg.main.Sprite;

public class SpriteList {
	
	public List<Sprite> list;
	
	public SpriteList() {
		list = new ArrayList<>();
	}
	
	public void add(Sprite child) {
		if(list != null) {
			list.add(child);
		}
	}
	
	public Sprite find(String name) {
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
