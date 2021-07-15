package basic;

public class Time extends Yield{

	public static double FPS;
	public static double FPSf;

	double n;
	double np;
	
	private static double startTimeMillis;
	
	double t;
	long c;
	
	//Getters e setters >
	
	public static double getFPS() {
		return FPS;
	}

	public static double getFPSf() {
		return FPSf;
	}

	public static double getStartTimeMillis() {
		return startTimeMillis;
	}
	
	//Getters e setters <
	
	public Time() {
		load(this);
		
		startTimeMillis = System.currentTimeMillis();
		c =  System.nanoTime();
	}

	public void Update() {
		n = System.nanoTime() - np;
		n = 1000000000 / n;

		np = System.nanoTime();

		FPS = n;

		t += 1;
		if (System.nanoTime() > c + 1000000000) {
			c = System.nanoTime();
			FPSf = t;
				System.out.println("FPS: " + FPSf);
			t = 0;
		}
	}

	public static double deltaTime() {
		double it = (((FPS * -1) + Settings.getGame_FPS()) / Settings.getGame_FPS()) + 1;
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
	
}
