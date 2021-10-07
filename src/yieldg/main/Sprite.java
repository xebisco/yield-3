package yieldg.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage image;
	private static String iconPath = "/logo300x300.png";
	public String path, name;
	public int height;
	public int width;
	public int addX, addY;

	public Sprite(String path) {
		if(path.charAt(0) != '/') {
			if(path != "yieldlogo")
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
				e1.printStackTrace();
			}
		}

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = path;
	}

	public Sprite(String path, String name) {
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

	public Sprite(BufferedImage image) {
		this.image = image;

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = image.toString();
	}
	
	public Sprite(BufferedImage image, String name) {
		this.image = image;

		this.height = (this.image.getHeight());
		this.width = (this.image.getWidth());
		this.name = name;
	}

	public Sprite getSpriteint(int x, int y, int width, int height) {
		Sprite ren = new Sprite(this.image.getSubimage(x, y, width, height));
		return ren;
	}

	public static String getIconPath() {
		return iconPath;
	}

	public static void setIconPath(String iconPath) {
		Sprite.iconPath = iconPath;
	}
}
