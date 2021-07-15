package yield;

import basic.Settings;
import basic.Yield;
import init.Init;
import util.graphics.Sprite;
import util.graphics.SpriteList;

public class Main extends Yield {

	public static void main(String[] args) {
		Settings.Window.Change(1280, 720, false);
		Settings.Engine.Change(1280, 720, 60);
		Settings.Info.Change("yield Window test", "vto", "1.0");
		Init.startApp();
	}

}
