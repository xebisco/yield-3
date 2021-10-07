package yield.engine.systems;

import java.awt.event.KeyEvent;

import yield.engine.YieldApp;
import yield.engine.systems.main.YieldSystem;
import yield.util.Input;
import yieldg.scripts.OldYieldOverlayScript;

public class OldOverlaySystem extends YieldSystem {

	private boolean canActive = true, active;
	private Input key;
	private static int defaultKey = KeyEvent.VK_F4;
	public OldYieldOverlayScript yieldOverlayScript;

	public OldOverlaySystem() {
		key = new Input(getDefaultKey());
		boolean create = true;
		for (int i = 0; i < YieldApp.app().getYieldScripts().size(); i++) {
			if (YieldApp.app().getYieldScripts().get(i).tag().equals("YieldOverlayScript")) {
				create = false;
				yieldOverlayScript = (OldYieldOverlayScript) YieldApp.app().getYieldScripts().get(i);
			}
		}
		if (create == true) {
			yieldOverlayScript = new OldYieldOverlayScript();
			yieldOverlayScript.overlaySystem = this;
		}
	}

	@Override
	public void tick() {
		if (key.isClicked()) {
			if (canActive) {
				if (isActive()) {
					setActive(false);
					yieldOverlayScript.setTipsF(yieldOverlayScript.getFrames());
					yieldOverlayScript.setTips(true);
					yieldOverlayScript.setXft(yieldOverlayScript.getSxft());
				} else {
					setActive(true);
					yieldOverlayScript.setXft(yieldOverlayScript.getSxft());
					yieldOverlayScript.getOpenOverlayAudio().play();
				}
			}
		}
	}

	public boolean isCanActive() {
		return canActive;
	}

	public void setCanActive(boolean canActive) {
		this.canActive = canActive;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static int getDefaultKey() {
		return defaultKey;
	}

	public static void setDefaultKey(int defaultKey) {
		OldOverlaySystem.defaultKey = defaultKey;
	}

	public Input getKey() {
		return key;
	}

	public void setKey(Input key) {
		this.key = key;
	}

}
