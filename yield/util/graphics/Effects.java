package util.graphics;

import basic.Time;
import basic.Yield;
import engine.yield.YieldMain;

public class Effects extends Yield{
	static int timep;
	static int forcep;

	public void Update() {
		if (timep > 0) {
			int r = Yield.random.nextInt((forcep * 2)) - forcep;

			YieldMain.getScreenMain().ofx = r;

			int r2 = Yield.random.nextInt((forcep * 2)) - forcep;

			YieldMain.getScreenMain().ofy = r2;

			timep--;
		}
		else {
			YieldMain.getScreenMain().ofx = 0;
			YieldMain.getScreenMain().ofy = 0;
		}
	}

	public static void Feye(double pixels, double force) {

		if (YieldMain.getScreenMain().outch < 1) {
			YieldMain.getScreenMain().outchforce = force;
			YieldMain.getScreenMain().outch = pixels;
		}

	}

	public static void Feye(double pixels, double force, boolean ignore) {

		if (ignore) {
			YieldMain.getScreenMain().outchforce = force;
			YieldMain.getScreenMain().outch = pixels;
		} else if (YieldMain.getScreenMain().outch < 1) {
			YieldMain.getScreenMain().outchforce = force;
			YieldMain.getScreenMain().outch = pixels;
		}

	}

	public static void shake(int force, double wtime) {

		timep = (int) (Time.FPS * wtime);
		forcep = force;

	}
}
