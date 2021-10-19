package yield;

import java.awt.Graphics2D;

import yield.core.YldCore;

public abstract class YldGScript extends YldScript {

	public void render(Graphics2D g) {
		
	}
	
	public final void updateRender(Graphics2D g) {
		if (getFrames() == 1) {
			YldCore.yieldpresentation = false;
		} else {
			render(g);
		}
	}
	
}
