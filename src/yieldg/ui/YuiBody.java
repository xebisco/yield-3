package yieldg.ui;

import yield.main.Body;

public final class YuiBody extends Body {

	private YuiCanvas canvas;

	public YuiBody() {
		addScript(setCanvas(new YuiCanvas()));
	}

	public void startCanvas() {
		
	}

	public YuiCanvas getCanvas() {
		return canvas;
	}

	public YuiCanvas setCanvas(YuiCanvas canvas) {
		this.canvas = canvas;
		return canvas;
	}

}
