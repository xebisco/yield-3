package yield;

import java.lang.reflect.Method;

import yield.core.YldCore;
import yield.exceptions.YldCallMethodException;

public abstract class YldScript {

	private int layer = -1;

	private int frames;

	private YldBody body;

	protected static YldCore yld = YldApp.core;
	
	protected static YldApp app = yld.app;

	public abstract String tag();
	
	public YldScript() {
		awake();
	}

	public void awake() {
		load();
	}

	public void start() {

	}

	public void tick() {

	}

	public void roomEnter() {

	}

	public final boolean load() {
		boolean success = true;
		try {
			yld.loadYldScript(this);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}

	public final void update() {
		frames++;
		if (frames == 1) {
			start();
		} else {
			tick();
		}
	}

	public static void callMethod(String methodText, YldScript script) {
		try {
			Method m = script.getClass().getDeclaredMethod(methodText);
			m.invoke(methodText);
		} catch (Exception e) {
			throw new YldCallMethodException(e.getMessage());
		}
	}

	public void callMethod(String methodText) {
		try {
			Method m = getClass().getDeclaredMethod(methodText);
			m.invoke(methodText);
		} catch (Exception e) {
			throw new YldCallMethodException(e.getMessage());
		}
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	/**
	 * @return the body
	 */
	public YldBody getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(YldBody body) {
		this.body = body;
	}

	/**
	 * @return the yld
	 */
	public static YldCore getYld() {
		return yld;
	}

	/**
	 * @param yld the yld to set
	 */
	public static void setYld(YldCore yld) {
		YldScript.yld = yld;
	}

}
