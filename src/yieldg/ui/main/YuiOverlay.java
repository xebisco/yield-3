package yieldg.ui.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import yield.engine.systems.SecondarySpriteSystem;
import yield.util.Input;
import yield.util.Time;
import yieldg.main.Sprite;
import yieldg.ui.YuiGraphical;

public class YuiOverlay extends YuiGraphical {

	private Input keyInput;
	private YuiRect rect;
	private YuiPalet palet;
	private final String yieldTitle = "yield";
	private Sprite yieldLogo = new Sprite("yieldlogo");
	private int titleX = 130, titleY = 20, sumBorderX = 80;
	private double x, y;
	private String[] elements = { "TPS", "RPS", "FTPS", "FRPS" };

	public YuiOverlay() {
		keyInput = new Input(KeyEvent.VK_F4);
		show = false;
	}

	@Override
	public void awake() {
		this.palet = canvas.getPalet();
		canvas.addGraphical(rect = new YuiRect());
		rect.show = false;
	}

	private int xSprite = 100;

	@Override
	public void tick() {
		rect.setHeight(900);
		rect.setX(x);
		rect.setY(y);
		xSprite = (int) (titleX + x - palet.f3.getSize() * 1.2);
	}

	@Override
	public void onKeyClick(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			if (!show) {
				show = true;
			} else {
				show = false;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(palet.f3);
		rect.setWidth(g.getFontMetrics().stringWidth(yieldTitle) + titleX + sumBorderX);
		rect.render(g);
		g.setColor(palet.c2);
		g.drawString(yieldTitle, (int) (x + titleX), (int) (y + g.getFont().getSize() + titleY));
		SecondarySpriteSystem.draw(yieldLogo, xSprite, (int) (titleY + y + g.getFont().getSize() * 0.15),
				g.getFont().getSize(), g.getFont().getSize(), g);
		int afaste = (int) (titleY * 7d + y);
		g.setFont(palet.f2);
		for (int i = 0; i < elements.length; i++) {
			String element = elements[i];

			String complement = " - ";
			switch (element) {
			case "TPS":
				complement = complement.concat(String.valueOf(Time.getSlowTickPS()));
				break;

			case "RPS":
				complement = complement.concat(String.valueOf(Time.getSlowRenderPS()));
				break;

			case "FTPS":
				complement = complement.concat(String.valueOf(Time.getTickPS()));
				break;

			case "FRPS":
				complement = complement.concat(String.valueOf(Time.getRenderPS()));
				break;

			default:
				complement = " ";
				break;
			}

			g.drawString(element.concat(complement),
					(int) ((xSprite * 2.3) - g.getFontMetrics().stringWidth(element.concat(complement)) / 2.5d),
					(i * (g.getFont().getSize() + 2) + afaste));
		}
	}

	public Input getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(Input keyInput) {
		this.keyInput = keyInput;
	}

	public YuiRect getRect() {
		return rect;
	}

	public void setRect(YuiRect rect) {
		this.rect = rect;
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

	public Sprite getYieldLogo() {
		return yieldLogo;
	}

	public void setYieldLogo(Sprite yieldLogo) {
		this.yieldLogo = yieldLogo;
	}

	public int getTitleX() {
		return titleX;
	}

	public void setTitleX(int titleX) {
		this.titleX = titleX;
	}

	public int getTitleY() {
		return titleY;
	}

	public void setTitleY(int titleY) {
		this.titleY = titleY;
	}

	public int getSumBorderX() {
		return sumBorderX;
	}

	public void setSumBorderX(int sumBorderX) {
		this.sumBorderX = sumBorderX;
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

	public String[] getElements() {
		return elements;
	}

	public void setElements(String[] elements) {
		this.elements = elements;
	}

	public String getYieldTitle() {
		return yieldTitle;
	}

}
