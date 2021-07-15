package init;

import java.util.Locale;

import engine.yield.YieldMain;

public class Init {
	
	public static YieldMain yieldMain;

	public static void startApp() {
		Locale.setDefault(Locale.US);
		
		yieldMain = new YieldMain();
	}
	
}
