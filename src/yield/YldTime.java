package yield;

import yield.display.YldGraphical;
import yield.objects.YldB;

import java.awt.*;

/**
 * Contem todas as informações sobre o tempo o primeiro YldCore criado.
 */
public final class YldTime extends YldB implements YldGraphical {

	private static int FPS, renderFPS;

	private double n;
	private double np;

	private double nR;
	private double npR;

	private static double startTimeMillis;

	@Override
	public void create() {
		startTimeMillis = System.currentTimeMillis();
	}

	@Override
	public void update() {
		n = System.nanoTime() - np;
		n = 1000000000 / n;

		np = System.nanoTime();

		FPS = (int) n;
	}

	@Override
	public void draw(Graphics g) {
		nR = System.nanoTime() - npR;
		nR = 1000000000 / nR;

		npR = System.nanoTime();

		renderFPS = (int) nR;
	}

	public static double deltaTime() {
		double it = ((((double) FPS * -1.0) + (double) Yld.fps) / (double) Yld.fps) * 2 + 1.0;
		return it;
	}

	public static double renderDeltaTime() {
		double it = ((((double) renderFPS * -1.0) + (double) Yld.renderFps) / (double) Yld.renderFps) * 2 + 1.0;
		return it;
	}

	public static int totalSeconds() {
		int s = (int) (System.currentTimeMillis() - startTimeMillis) / 1000;
		return s;
	}

	public static double totalMilliSeconds() {
		int s = (int) (System.currentTimeMillis() - startTimeMillis);
		return s;
	}

	public static double getStartTimeMillis() {
		return startTimeMillis;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getNp() {
		return np;
	}

	public void setNp(double np) {
		this.np = np;
	}

	public double getnR() {
		return nR;
	}

	public void setnR(double nR) {
		this.nR = nR;
	}

	public double getNpR() {
		return npR;
	}

	public void setNpR(double npR) {
		this.npR = npR;
	}

	public static void setStartTimeMillis(double startTimeMillis) {
		YldTime.startTimeMillis = startTimeMillis;
	}

	public static int getFPS() {
		return FPS;
	}

	public static void setFPS(int FPS) {
		YldTime.FPS = FPS;
	}

	public static int getRenderFPS() {
		return renderFPS;
	}

	public static void setRenderFPS(int renderFPS) {
		YldTime.renderFPS = renderFPS;
	}
}
