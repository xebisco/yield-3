package yieldg.ui.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import yieldg.ui.YuiGraphical;

public class YuiRect extends YuiGraphical {

	private int width = 128, height = 128;
	private double x, y;
	private YuiPalet palet;
	private Color mainColor;

	public YuiRect() {
		
	}

	public void awake() {
		this.palet = canvas.palet;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
			g2.setColor(palet.c3);
			if (mainColor != null)
				g2.setColor(mainColor);
			g2.fillRect((int) x, (int) y, width, height);
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

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public YuiPalet getPalet() {
		return palet;
	}

	public void setPalet(YuiPalet palet) {
		this.palet = palet;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Color getMainColor() {
		return mainColor;
	}

	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}

}
