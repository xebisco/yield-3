package yield.engine.systems.main;

import java.util.ArrayList;
import java.util.List;

import yield.engine.systems.SecondarySpriteSystem;

public final class SYS {

	private static List<YieldSystem> systems;

	public SYS() {
		systems = new ArrayList<>();
	}
	
	public static void startDefaultSystems() {
		//add(YieldApp.app().getWindow().getInputSystem());
		//add(new OldOverlaySystem());
		add(new SecondarySpriteSystem());
	}

	public static List<YieldSystem> getSystems() {
		return systems;
	}

	public static void setSystems(List<YieldSystem> systems) {
		SYS.systems = systems;
	}

	public static void add(YieldSystem system) {
		getSystems().add(system);
	}

	public static class Sys {
		public void add(YieldSystem system) {
			SYS.add(system);
		}
	}

}
