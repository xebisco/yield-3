package util.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.yield.YieldMain;

public class Sprite {
	// VTO Original vxEngine

	private BufferedImage image;
	public String path;
	public int height;
	public int width;

	public Sprite(String path) {
		try {
			if (ImageIO.read(getClass().getResource(path)) != null) {
				this.image = ImageIO.read(getClass().getResource(path));
				this.path = path;
			} else {
				this.image = ImageIO.read(getClass().getResource("engine/resourses/logo300x300.png"));
				this.path = "/engine/resourses/logo300x300.png";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
	}

	public Sprite(BufferedImage image) {
		this.image = image;

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
	}

	public Sprite getSpriteint(int x, int y, int width, int height) {
		Sprite ren = new Sprite(this.image.getSubimage(x, y, width, height));
		return ren;
	}

	// render
	public void draw(int x, int y, int width, int height) {
		YieldMain.getScreenMain().getMainGraphics().drawImage(this.image, x, y, width, height, null);
	}
}
