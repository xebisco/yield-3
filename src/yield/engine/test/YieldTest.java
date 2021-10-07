package yield.engine.test;

import yield.engine.YieldApp;
import yield.engine.test.bodies.FloatingYieldLogo;

public class YieldTest {

	public YieldTest() {
		if (YieldApp.app() == null) {
			new YieldApp().start(1280, 720);
		} else {
			YieldApp.app().setWidth(1280);
			YieldApp.app().setHeight(720);
		}
		new FloatingYieldLogo();
	}
	
	/*public static void main(String[] args) {
		new YieldTest();
	}*/
}
