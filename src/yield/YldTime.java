package yield;

import yield.display.YldGraphical;
import yield.objects.YldB;

import java.awt.*;

public final class YldTime extends YldB implements YldGraphical {

	private static int FPS, renderFPS;

	private double n, np, nR, npR, elapsed, renderElapsed;

	private static double startTimeMillis;

	public YldTime() {
		load();
	}

	@Override
	public void create() {
		startTimeMillis = System.currentTimeMillis();
	}

	@Override
	public void update() {
		n = System.nanoTime() - np;
		elapsed = n;
		n = 1000000000 / n;

		np = System.nanoTime();

		FPS = (int) n;
	}

	@Override
	public void draw(Graphics g) {
		nR = System.nanoTime() - npR;
		renderElapsed = nR;
		nR = 1000000000 / nR;

		npR = System.nanoTime();

		renderFPS = (int) nR;
	}

	public static double deltaTime() {
		return ((((double) FPS * -1.0) + (double) Yld.getFps()) / (double) Yld.getFps()) * 2 + 1.0;
	}

	public static double renderDeltaTime() {
		return ((((double) renderFPS * -1.0) + (double) Yld.getRenderFps()) / (double) Yld.getRenderFps()) * 2 + 1.0;
	}

	public static int totalSeconds() {
		return (int) (System.currentTimeMillis() - startTimeMillis) / 1000;
	}

	public static double totalMilliSeconds() {
		return (int) (System.currentTimeMillis() - startTimeMillis);
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

	public double getnR() {
		return nR;
	}

	public void setnR(double nR) {
		this.nR = nR;
	}

	public double getElapsed() {
		return elapsed;
	}

	public void setElapsed(double elapsed) {
		this.elapsed = elapsed;
	}

	public double getRenderElapsed() {
		return renderElapsed;
	}

	public void setRenderElapsed(double renderElapsed) {
		this.renderElapsed = renderElapsed;
	}
}
