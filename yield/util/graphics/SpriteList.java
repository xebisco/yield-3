package util.graphics;

import java.util.ArrayList;
import java.util.List;

public class SpriteList {

	private String name;
	
	public List<SpriteChild> list;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SpriteList(String name) {
		this.setName(name);
		list = new ArrayList<>();
	}
	
	public void add(SpriteChild child) {
		if(list != null) {
			list.add(child);
		}
	}
	
	public SpriteChild find(String name) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				return list.get(i);
			}
		}
		return null;
	}
	
}
