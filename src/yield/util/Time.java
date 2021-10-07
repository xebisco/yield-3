package yield.util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import yield.engine.YieldApp;
import yield.main.YieldScript;

public class Time extends YieldScript {

	private static int renderPS;
	private static int tickPS;
	private static int slowRenderPS;
	private static int slowTickPS;
	private int maxListsSize = 100;
	
	private static List<Integer> TPSs = new ArrayList<>();
	private static List<Integer> RPSs = new ArrayList<>();

	private double n;
	private double np;

	private double nR;
	private double npR;

	private static double startTimeMillis;

	public Time() {
		startTimeMillis = System.currentTimeMillis();
		// load();
	}

	@Override
	public void awake() {
		// autoLoad = false;
		// load(this);
	}

	@Override
	public void tick() {
		if(TPSs.size() > maxListsSize) {
			TPSs.clear();
		}
		n = System.nanoTime() - np;
		n = 1000000000 / n;

		np = System.nanoTime();

		tickPS = (int) n;
		Time.getTPSs().add(tickPS);
	}

	@Override
	public void render(Graphics g) {
		if(RPSs.size() > maxListsSize) {
			RPSs.clear();
		}
		nR = System.nanoTime() - npR;
		nR = 1000000000 / nR;

		npR = System.nanoTime();

		renderPS = (int) nR;
		Time.getRPSs().add(renderPS);
	}

	public static double deltaTime() {
		double it = (((tickPS * -1.0) + (double) YieldApp.app().getTPS())
				/ (double) YieldApp.app().getTPS()) * 2 + 1.0;
		if(it > 0.5 && it < 2)
		return it;
		return 1;
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

	public static int getRenderPS() {
		return renderPS;
	}

	public static int getTickPS() {
		return tickPS;
	}

	@Override
	public String tag() {
		return "EngineTime";
	}

	public static int getSlowTickPS() {
		return slowTickPS;
	}

	public static void setSlowTickPS(int slowTickPS) {
		Time.slowTickPS = slowTickPS;
	}

	public static int getSlowRenderPS() {
		return slowRenderPS;
	}

	public static void setSlowRenderPS(int slowRenderPS) {
		Time.slowRenderPS = slowRenderPS;
	}

	public static List<Integer> getTPSs() {
		return TPSs;
	}

	public static void setTPSs(List<Integer> tPSs) {
		TPSs = tPSs;
	}

	public static List<Integer> getRPSs() {
		return RPSs;
	}

	public static void setRPSs(List<Integer> rPSs) {
		RPSs = rPSs;
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

	public static void setRenderPS(int renderPS) {
		Time.renderPS = renderPS;
	}

	public static void setTickPS(int tickPS) {
		Time.tickPS = tickPS;
	}

	public static void setStartTimeMillis(double startTimeMillis) {
		Time.startTimeMillis = startTimeMillis;
	}

	public int getMaxListsSize() {
		return maxListsSize;
	}

	public void setMaxListsSize(int maxListsSize) {
		this.maxListsSize = maxListsSize;
	}

}
