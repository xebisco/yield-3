package yieldg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import yieldg.util.HotPoint;

public class YldSprite {
	
	public static final String SPRITE_VERSION = "1.2b";

	private BufferedImage image;
	private static String iconPath = "/Yield Icon.png";
	public String path, name;
	public int height;
	public int width;
	public int addX, addY;

	public YldSprite(String path) {
		if(path.charAt(0) != '/' && path != "yieldlogo") {
			path = "/" + path;
		}
		try {
			this.image = ImageIO.read(getClass().getResource(path));
			this.path = path;
		} catch (Exception e) {
			if(path != "yieldlogo")
			System.out.println("Can't find " + path + " image.");
			try {
				this.image = ImageIO.read(getClass().getResource(getIconPath()));
				this.path = getIconPath();
			} catch (IOException e1) {
				System.out.println("Can't find " + this.path + " image.");
			}
		}

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = path;
	}

	public YldSprite(String path, String name) {
		if(path.charAt(0) != '/') {
			if(path != "yieldlogo")
			path = "/" + path;
		}
		try {
			this.image = ImageIO.read(getClass().getResource(path));
			this.path = path;
		} catch (Exception e) {
			if(path != "yieldlogo")
			System.out.println("Can't find " + getClass().getResource(path) + " image.");
			try {
				this.image = ImageIO.read(getClass().getResource(getIconPath()));
				this.path = getIconPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = name;
	}

	
	public BufferedImage getBufferedImage() {
		return image;
	}

	public void draw(int x, int y, Graphics2D g) {
		g.drawImage(getBufferedImage(), x + addX, y + addY, null);
	}

	public void draw(int x, int y, HotPoint hotPoint, Graphics2D g) {
		if (hotPoint.equals(HotPoint.MIDDLE)) {
			x = (int) (x - (double) width / 2d);
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_LEFT)) {
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_RIGHT)) {
			x = (int) (x + (double) width / 2d);
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.UP)) {
			x = (int) (x - (double) width / 2d);
		} else if (hotPoint.equals(HotPoint.UP_RIGHT)) {
			x = (int) (x - (double) width);
		} else if (hotPoint.equals(HotPoint.DOWN)) {
			x = (int) (x - (double) width / 2d);
			y = (int) (y - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_RIGHT)) {
			x = (int) (x - (double) width);
			y = (int) (y - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_LEFT)) {
			y = (int) (y - (double) height);
		}
		g.drawImage(getBufferedImage(), x + addX, y + addY, null);
	}

	public void draw(int x, int y, int width, int height, HotPoint hotPoint, Graphics2D g) {
		if (hotPoint.equals(HotPoint.MIDDLE)) {
			x = (int) (x - (double) width / 2d);
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_LEFT)) {
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_RIGHT)) {
			x = (int) (x + (double) width / 2d);
			y = (int) (y - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.UP)) {
			x = (int) (x - (double) width / 2d);
		} else if (hotPoint.equals(HotPoint.UP_RIGHT)) {
			x = (int) (x - (double) width);
		} else if (hotPoint.equals(HotPoint.DOWN)) {
			x = (int) (x - (double) width / 2d);
			y = (int) (y - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_RIGHT)) {
			x = (int) (x - (double) width);
			y = (int) (y - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_LEFT)) {
			y = (int) (y - (double) height);
		}
		g.drawImage(getBufferedImage(), x + addX, y + addY, width, height, null);
	}

	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.drawImage(getBufferedImage(), x + addX, y + addY, width, height, null);
	}

	public YldSprite(BufferedImage image) {
		this.image = image;

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = image.toString();
	}
	
	public YldSprite(BufferedImage image, String name) {
		this.image = image;

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = name;
	}

	public YldSprite getSpriteint(int x, int y, int width, int height) {
		YldSprite ren = new YldSprite(this.image.getSubimage(x, y, width, height));
		return ren;
	}

	public static String getIconPath() {
		return iconPath;
	}

	public static void setIconPath(String iconPath) {
		YldSprite.iconPath = iconPath;
	}
	
	
	
}
