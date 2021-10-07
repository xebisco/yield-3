package yieldg.ui.main;

import java.awt.Graphics;

import yield.main.Script;
import yield.util.Mouse;
import yieldg.ui.YuiGraphical;

public class YuiButton extends YuiGraphical {

	private int width, height, drawWidth, drawHeight;
	private double x, y;
	private double x1, y1, width1, height1;
	private String text = "Yield Button";
	private YuiPalet palet;
	private boolean mouseIn;
	private Script scriptOnClick;

	public YuiButton() {

	}

	public YuiButton(String text) {
		this.text = text;
	}

	public void awake() {
		this.palet = canvas.palet;
		drawWidth = width;
		drawHeight = height;
	}

	@Override
	public void render(Graphics g) {
			x1 = x;
			y1 = y;
			width1 = drawWidth;
			height1 = drawHeight;
			g.setFont(palet.f2);
			if (width == 0) {
				drawWidth = g.getFontMetrics().stringWidth(text) + 10;
			} else {
				drawWidth = width;
			}
			if (height == 0) {
				drawHeight = palet.f2.getSize() + 4;
			} else {
				drawHeight = height;
			}
			x1 *= canvas.getmWidth();
			y1 *= canvas.getmHeight();
			width1 *= canvas.getmWidth();
			height1 *= canvas.getmHeight();
			g.setColor(palet.c3);
			g.fillRect((int) x, (int) y, drawWidth, drawHeight);
			g.setColor(palet.c2);
			if (mouseIn)
				g.setColor(palet.c4);
			int xt = (int) ((((double) drawWidth / 2d) + x) - g.getFontMetrics().stringWidth(text) / 2d),
					yt = palet.f2.getSize() / 2 - (int) (y - ((double) drawHeight / 2d)) - 2;
			g.drawString(text, xt, yt);
			g.setColor(palet.c6);
			if (mouseIn)
				g.setColor(palet.c5);
			g.drawRect((int) x, (int) y, drawWidth, drawHeight);

			if (Mouse.isTouching(x1, y1, width1, height1)) {
				setMouseIn(true);
			} else {
				setMouseIn(false);
			}
	}
	
	@Override
	public void onMouseClick() {
		if(mouseIn) {
			scriptOnClick.onClick();
		}
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

	public int getDrawWidth() {
		return drawWidth;
	}

	public void setDrawWidth(int drawWidth) {
		this.drawWidth = drawWidth;
	}

	public int getDrawHeight() {
		return drawHeight;
	}

	public void setDrawHeight(int drawHeight) {
		this.drawHeight = drawHeight;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public boolean isMouseIn() {
		return mouseIn;
	}

	public void setMouseIn(boolean mouseIn) {
		this.mouseIn = mouseIn;
	}

	public YuiPalet getPalet() {
		return palet;
	}

	public void setPalet(YuiPalet palet) {
		this.palet = palet;
	}

	public Script getScriptOnClick() {
		return scriptOnClick;
	}

	public void setScriptOnClick(Script scriptOnClick) {
		this.scriptOnClick = scriptOnClick;
	}

}
