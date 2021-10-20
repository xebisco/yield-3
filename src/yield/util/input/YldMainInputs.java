package yield.util.input;

import yield.YldScript;

public class YldMainInputs extends YldScript {

	@Override
	public String tag() {
		load();
		return "YldMainInputs";
	}

	public static int horizontal, vertical;

	@Override
	public void tick() {
		if (YldInput.isKeyPressed("d") || YldInput.isKeyPressed("right")) {
			horizontal = 1;
		} else if (YldInput.isKeyPressed("a") || YldInput.isKeyPressed("left")) {
			horizontal = -1;
		} else {
			horizontal = 0;
		}

		if (YldInput.isKeyPressed("s") || YldInput.isKeyPressed("down")) {
			vertical = 1;
		} else if (YldInput.isKeyPressed("w") || YldInput.isKeyPressed("up")) {
			vertical = -1;
		} else {
			vertical = 0;
		}
	}

}
