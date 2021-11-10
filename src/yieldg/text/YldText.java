package yieldg.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import yield.YldGScript;
import yieldg.util.HotPoint;

/**
 * Esse YldScript mostrará um texto na janela
 */
public class YldText extends YldGScript {

    private String text;
    private double x, y;
    private Font font = new Font("arial", Font.BOLD, 50);
    private Color color = Color.white;
    private HotPoint hotPoint = HotPoint.NONE;
    private int hx, hy;
    private int width, height;

    @Override
    public String tag() {
        return "YldText";
    }

    public YldText(String text) {
        this.text = text;
    }

    public YldText(String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public YldText() {

    }

    @Override
    public void tick() {
        if (hotPoint.equals(HotPoint.MIDDLE)) {
			hx = (int) (0 - (double) width / 2d);
			hy = (int) (0 - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_LEFT)) {
			hy = (int) (0 - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.MIDDLE_RIGHT)) {
			hx = (int) (0 + (double) width / 2d);
			hy = (int) (0 - (double) height / 2d);
		} else if (hotPoint.equals(HotPoint.UP)) {
			hx = (int) (0 - (double) width / 2d);
		} else if (hotPoint.equals(HotPoint.UP_RIGHT)) {
			hx = (int) (0 - (double) width);
		} else if (hotPoint.equals(HotPoint.DOWN)) {
			hx = (int) (0 - (double) width / 2d);
			hy = (int) (0 - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_RIGHT)) {
			hx = (int) (0 - (double) width);
			hy = (int) (0 - (double) height);
		} else if (hotPoint.equals(HotPoint.DOWN_LEFT)) {
			hy = (int) (0 - (double) height);
		}
    }

    @Override
    public void render(Graphics2D g) {
        Color previousColor = g.getColor();
        Font previousFont = g.getFont();
        g.setFont(font);
        g.setColor(color);
        width = g.getFontMetrics().stringWidth(text);
        height = g.getFont().getSize();
        g.drawString(text, (int) (x + hx), (int) (y + g.getFont().getSize() / 2d + hy));
        g.setColor(previousColor);
        g.setFont(previousFont);
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

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HotPoint getHotPoint() {
        return hotPoint;
    }

    public void setHotPoint(HotPoint hotPoint) {
        this.hotPoint = hotPoint;
    }

    public int getHx() {
        return hx;
    }

    public void setHx(int hx) {
        this.hx = hx;
    }

    public int getHy() {
        return hy;
    }

    public void setHy(int hy) {
        this.hy = hy;
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

}
