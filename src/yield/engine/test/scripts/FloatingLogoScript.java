package yield.engine.test.scripts;

import java.awt.Color;

import yield.engine.YieldApp;
import yield.main.Room;
import yield.main.Script;
import yield.scripts.Transform;
import yield.util.Time;
import yieldg.main.Sprite;
import yieldg.scripts.ObjectInScreen;
import yieldg.ui.YuiBody;
import yieldg.ui.main.YuiOverlay;

public class FloatingLogoScript extends Script {

	@Override
	public String tag() {
		return "FloatingLogoScript";
	}

	private double xspeedbase = 3, yspeedbase = 3, xspeed = xspeedbase, yspeed = yspeedbase;
	private ObjectInScreen objectInScreen;
	private Transform transform;
	YuiOverlay overlay;

	@Override
	public void start() {
		YuiBody yuiBody;
		Room.getActRoom().addBody(yuiBody = new YuiBody());
		yuiBody.getCanvas().addGraphical(overlay = new YuiOverlay());
		YieldApp.app().setBgColor(new Color(5, 0, 20));
		YieldApp.app().setTPS(60);
		YieldApp.app().setRPS(60);
		objectInScreen = ((ObjectInScreen) getBody().getScript("ObjectInScreen"));
		transform = ((Transform) getBody().getScript("Transform"));
		objectInScreen.setSprite(new Sprite("YieldPresentation.png"));

		transform.position.setX(xspeedbase);
		transform.position.setY(yspeedbase);
	}
	
	@Override
	public void onClick() {
		transform.position.setX(xspeedbase);
		transform.position.setY(yspeedbase);
	}

	@Override
	public void tick() {
		//overlay.show = true;
		transform.position
				.setX((transform.position.getX() + (xspeed * (30d / YieldApp.app().getTPS()) * Time.deltaTime())));
		transform.position
				.setY((transform.position.getY() + (yspeed) * (30d / YieldApp.app().getTPS()) * Time.deltaTime()));
		if (!objectInScreen.inScreenXplus()) {
			xspeed = xspeedbase * -1;
		}
		if (!objectInScreen.inScreenYplus()) {
			yspeed = yspeedbase * -1;
		}
		if (!objectInScreen.inScreenXminus()) {
			xspeed = xspeedbase;
		}
		if (!objectInScreen.inScreenYminus()) {
			yspeed = yspeedbase;
		}
	}

}
