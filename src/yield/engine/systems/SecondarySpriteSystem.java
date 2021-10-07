package yield.engine.systems;

import java.awt.Graphics;

import yield.engine.systems.main.YieldSystem;
import yieldg.main.Sprite;

public class SecondarySpriteSystem extends YieldSystem {
	
	public static void draw(Sprite sprite, int x, int y, Graphics g) {
		g.drawImage(sprite.getBufferedImage(), x, y, sprite.width, sprite.height, null);
	}
	
	public static void draw(Sprite sprite, int x, int y, int width, int height, Graphics g) {
		g.drawImage(sprite.getBufferedImage(), x, y, width, height, null);
	}

}
