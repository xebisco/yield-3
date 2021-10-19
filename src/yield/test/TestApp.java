package yield.test;

import yield.YldApp;

public class TestApp extends YldApp {
	
	@Override
	public void start() {
		startYield();
		new FloatingYieldLogo();
	}
	
	public static void main(String[] args) {
		new TestApp();
	}

}
