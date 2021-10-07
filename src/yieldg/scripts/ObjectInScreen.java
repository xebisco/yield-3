package yieldg.scripts;

import java.awt.Graphics;

import yield.engine.YieldApp;
import yield.main.Script;
import yield.scripts.Transform;
import yieldg.enums.HotPoint;
import yieldg.main.Sprite;

public class ObjectInScreen extends Script {

	@Override
	public String tag() {
		return "ObjectInScreen";
	}

	private boolean spriteSized = true;
	private Sprite sprite;
	private int x, y, width, height;
	private Transform transform;
	public HotPoint hotPoint = HotPoint.NONE;
	public double addX, addY;
	private boolean inverted;

	@Override
	public void start() {
		if (getBody() != null) {
			transform = (Transform) getBody().getScript("Transform");
		}
	}

	@Override
	public void render(Graphics g) {
		if(spriteSized && getSprite() != null) {
			width = getSprite().width;
			height = getSprite().height;
			transform.scale.setWidth(width);
			transform.scale.setWidth(height);
			
		} else {
			width = transform.scale.getWidth();
			height = transform.scale.getHeight();
		}
		if (hotPoint.equals(HotPoint.NONE)) {
			x = (int) (transform.position.getX());
			y = (int) (transform.position.getY());
		} else if (hotPoint.equals(HotPoint.MIDDLE)) {
			x = (int) (transform.position.getX() - (double) width / 2d);
			y = (int) (transform.position.getY() - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_LEFT)) {
			x = (int) (transform.position.getX());
			y = (int) (transform.position.getY() - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_RIGHT)) {
			x = (int) (transform.position.getX() + (double) width / 2d);
			y = (int) (transform.position.getY() - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.UP)) {
			x = (int) (transform.position.getX() - (double) width / 2d);
			y = (int) (transform.position.getY());
		} else if (hotPoint.equals(HotPoint.UP_LEFT)) {
			x = (int) (transform.position.getX());
			y = (int) (transform.position.getY());
		} else if (hotPoint.equals(HotPoint.UP_RIGHT)) {
			x = (int) (transform.position.getX() - (double) width);
			y = (int) (transform.position.getY());
		} else if (hotPoint.equals(HotPoint.DOWN)) {
			x = (int) (transform.position.getX() - (double) width / 2d);
			y = (int) (transform.position.getY() - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_RIGHT)) {
			x = (int) (transform.position.getX() - (double) width);
			y = (int) (transform.position.getY() - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_LEFT)) {
			x = (int) (transform.position.getX());
			y = (int) (transform.position.getY() - (double) height);
		}
		if (transform != null) {
			if (getSprite() != null) {
				if(inverted) {
					g.drawImage(getSprite().getBufferedImage(), (int) (x + addX + sprite.addX + width), (int) (y + addY + sprite.addY), width * -1, height, null);
				} else {
					g.drawImage(getSprite().getBufferedImage(), (int) (x + addX + sprite.addX), (int) (y + addY + sprite.addY), width, height, null);	
				}
			}
		}
	}
	
	public boolean inScreen() {
		if (getX() - transform.scale.getWidth() > YieldApp.app().getWidth() || getX() < 0) {
			return false;
		}
		if (getY() + transform.scale.getHeight() > YieldApp.app().getHeight() || getY() < 0) {
			return false;
		}
		return true;
	}

	public boolean inScreenX() {
		if (getX() + getWidth() > YieldApp.app().getWidth() || getX() < 0) {
			return false;
		}
		return true;
	}

	public boolean inScreenY() {
		if (getY() + getHeight() > YieldApp.app().getHeight() || getY() < 0) {
			return false;
		}
		return true;
	}

	public boolean inScreenXplus() {
		if (getX() +getWidth() > YieldApp.app().getWidth()) {
			return false;
		}
		return true;
	}

	public boolean inScreenYplus() {
		if (getY() + getHeight() > YieldApp.app().getHeight()) {
			return false;
		}
		return true;
	}

	public boolean inScreenXminus() {
		if (getX() < 0) {
			return false;
		}
		return true;
	}

	public boolean inScreenYminus() {
		if (getY() < 0) {
			return false;
		}
		return true;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isSpriteSized() {
		return spriteSized;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public HotPoint getHotPoint() {
		return hotPoint;
	}

	public void setHotPoint(HotPoint hotPoint) {
		this.hotPoint = hotPoint;
	}

	public double getAddX() {
		return addX;
	}

	public void setAddX(double addX) {
		this.addX = addX;
	}

	public double getAddY() {
		return addY;
	}

	public void setAddY(double addY) {
		this.addY = addY;
	}

	public void setSpriteSized(boolean spriteSized) {
		this.spriteSized = spriteSized;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

}
