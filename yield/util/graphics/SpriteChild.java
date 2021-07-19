package util.graphics;

public class SpriteChild {

	private int x, y, width, height, hx, hy;
	
	private String name;

	private Sprite mum, me;

	// Getters e setters >

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

	public void setHeight(int heigth) {
		this.height = heigth;
	}

	public int getHotPointX() {
		return hx;
	}

	public void setHotPointX(int hx) {
		this.hx = hx;
	}

	public int getHotPointY() {
		return hy;
	}

	public void setHotPointY(int hy) {
		this.hy = hy;
	}

	public Sprite getMumSprite() {
		return mum;
	}

	public void setMumSprite(Sprite mum) {
		this.mum = mum;
	}

	public Sprite getMe() {
		return me;
	}

	public void setMe(Sprite me) {
		this.me = me;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getters e setters <

	public SpriteChild(int x, int y, int width, int height, Sprite mum) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mum = mum;

		me = mum.getSpriteint(x, y, width, height);
	}

	public void draw(int x, int y) {
		if(me != null)
		me.draw(x - hx, y - hy, me.width, me.height);
	}
	public void draw(int x, int y, int width, int height) {
		if(me != null)
		me.draw(x - hx, y - hy, width, height);
	}
	public void draw(int x, int y, int width, int height, String arg) {
		if(arg.equals("IGNORE_HOT_POINT")) {
			if(me != null)
			me.draw(x, y, me.width, me.height);
		} else {
			if(me != null)
				me.draw(x - hx, y - hy, me.width, me.height);
		}
	}

	public void setHotPoint(int hx, int hy) {
		this.hx = hx;
		this.hy = hy;
	}

	public void setHotPoint(String arg) {
		if (arg.equals("MIDDLE")) {
			hx = width / 2;
			hy = height / 2;
		} else if (arg.equals("MIDDLE_DOWN")) {
			hx = width / 2;
			hy = height;
		} else if (arg.equals("MIDDLE_UP")) {
			hx = width / 2;
			hy = 0;
		} else if (arg.equals("LEFT")) {
			hx = 0;
			hy = height / 2;
		} else if (arg.equals("LEFT_DOWN")) {
			hx = 0;
			hy = height;
		} else if (arg.equals("LEFT_UP")) {
			hx = 0;
			hy = 0;
		} else if (arg.equals("RIGHT")) {
			hx = width;
			hy = height / 2;
		} else if (arg.equals("RIGHT_DOWN")) {
			hx = width;
			hy = height;
		} else if (arg.equals("RIGHT_UP")) {
			hx = width;
			hy = 0;
		}
	}
	
	public String toString() {
		return name;
	}

}
