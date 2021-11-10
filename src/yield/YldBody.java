package yield;

import java.util.ArrayList;
import java.util.List;

/**
 * Uma coletânia de YldScripts
 */
public abstract class YldBody {
	
	private List<YldScript> scriptList;
	private YldRoom room;

	public YldBody() {
		scriptList = new ArrayList<YldScript>();
		awake();
	}

	public abstract void awake();
	
	public final List<YldScript> getScriptList() {
		return scriptList;
	}

	public final void setScriptList(List<YldScript> scriptList) {
		this.scriptList = scriptList;
	}

	public final YldScript getScript(String tag) {
		for (int i = 0; i < getScriptList().size(); i++) {
			YldScript script = getScriptList().get(i);
			if (script.tag().equals(tag)) {
				return script;
			}
		}
		return null;
	}
	
	public final void onR() {
		for(int i = 0; i < getScriptList().size(); i++) {
			YldScript script = getScriptList().get(i);
			script.roomEnter();
		}
	}

	public final void addScript(YldScript script) {
		getScriptList().add(script);
		script.setBody(this);
	}

	public YldRoom getRoom() {
		return room;
	}

	public void setRoom(YldRoom room) {
		this.room = room;
	}

}
