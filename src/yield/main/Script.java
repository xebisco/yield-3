package yield.main;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import yield.main.interfaces.TagInterface;

public abstract class Script implements TagInterface {

	private int layer = -1;

	private Body body;

	private boolean render = true;

	private int frames;

	public Script() {
		awake();
	}

	public void awake() {

	}

	public void start() {

	}

	public void tick() {

	}

	public void tickBPM() {

	}

	public void render(Graphics g) {

	}

	public final void update() {
		frames++;
	}

	public void onClick() {

	}

	public void callMethod(String methodText, Script script) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = methodText.getClass().getDeclaredMethod(methodText);
		m.invoke(methodText);
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

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

}
