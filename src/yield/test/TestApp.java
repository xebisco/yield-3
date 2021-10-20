package yield.test;

import java.awt.Dimension;

import yield.YldApp;

public class TestApp extends YldApp {
	
	@Override
	public void start() {
		resolutionDimension = new Dimension(1280, 720);
		startYield();
		//new FloatingYieldLogo();
	}
	
	public static void main(String[] args) {
		new TestApp();
	}

}
