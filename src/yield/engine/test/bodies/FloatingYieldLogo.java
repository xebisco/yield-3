package yield.engine.test.bodies;

import yield.engine.test.scripts.FloatingLogoScript;
import yield.main.Body;
import yieldg.scripts.ObjectInScreen;

public class FloatingYieldLogo extends Body {
	
	public FloatingYieldLogo() {
		addScript(new ObjectInScreen());
		addScript(new FloatingLogoScript());
	}

}
