package yield.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yield.engine.YieldApp;
import yield.scripts.Transform;

public class Body {

	private List<Script> scriptList;
	private Room room;
	public Transform transform;
	private int layer = -1;

	public Body() {
		scriptList = new ArrayList<>();
		Room.defaultRoom.addBody(this);
		addScript(transform = new Transform());
	}

	public final void update() {
		for (int i = 0; i < getScriptList().size(); i++) {
			Script script = getScriptList().get(i);
			YieldApp.app().processScript(script);
			if (script.getLayer() >= 0) {
				Collections.swap(getScriptList(), script.getLayer(), i);
				script.setLayer(-1);
			}
		}
	}

	public final void renderer(Graphics g) {
		for (int i = 0; i < getScriptList().size(); i++) {
			Script script = getScriptList().get(i);
			if(script.isRender());
			YieldApp.app().processScript(script, g);
		}
	}

	public final List<Script> getScriptList() {
		return scriptList;
	}

	public final void setScriptList(List<Script> scriptList) {
		this.scriptList = scriptList;
	}

	public final Script getScript(String tag) {

		for (int i = 0; i < getScriptList().size(); i++) {
			Script script = getScriptList().get(i);
			if (script.tag().equals(tag)) {
				return script;
			}
		}

		return null;

	}

	public final void addScript(Script script) {
		getScriptList().add(script);
		script.setBody(this);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

}
