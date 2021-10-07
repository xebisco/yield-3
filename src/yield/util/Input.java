package yield.util;

import yield.engine.YieldApp;
import yield.engine.systems.InputSystem;

public class Input {

	private int key;

	private boolean pressed;

	private boolean clicked;

	private boolean invertedClicked;

	private int cf;

	private YieldApp yieldApp;

	public Input(int key) {
		this.key = key;
		set();
	}

	private void set() {
		if (getYieldApp() == null) {
			setYieldApp(YieldApp.app());
		}
		InputSystem.getInputs().add(this);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public YieldApp getYieldApp() {
		return yieldApp;
	}

	public void setYieldApp(YieldApp yieldApp) {
		this.yieldApp = yieldApp;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;

	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public int getCf() {
		return cf;
	}

	public void setCf(int cf) {
		this.cf = cf;
	}

	public boolean isInvertedClicked() {
		return invertedClicked;
	}

	public void setInvertedClicked(boolean invertedClicked) {
		this.invertedClicked = invertedClicked;
	}

}
