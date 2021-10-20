package yield.util;

import java.awt.Graphics2D;

import yield.YldGScript;

public final class YldTime extends YldGScript {

	@Override
	public String tag() {
		load();
		return "Time";
	}

	public static int FPS;
	public static int TPS;

	private double n;
	private double np;

	private double nR;
	private double npR;

	private static double startTimeMillis;

	@Override
	public void start() {
		startTimeMillis = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		n = System.nanoTime() - np;
		n = 1000000000 / n;

		np = System.nanoTime();

		TPS = (int) n;
	}

	@Override
	public void render(Graphics2D g) {
		nR = System.nanoTime() - npR;
		nR = 1000000000 / nR;

		npR = System.nanoTime();

		FPS = (int) nR;
	}

	public static double deltaTime() {
		double it = ((((double) TPS * -1.0) + (double) yld.getYldLogicEngine().getTargetTPS()) / (double) yld.getYldLogicEngine().getTargetTPS()) * 2 + 1.0;
		return it;
	}

	public static double renderDeltaTime() {
		double it = ((((double) FPS * -1.0) + (double) yld.getYldGraphicsEngine().getTargetFPS()) / (double) yld.getYldGraphicsEngine().getTargetFPS()) * 2 + 1.0;
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

}
