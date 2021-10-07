package yield.util;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import yield.engine.YieldApp;
import yield.main.YieldScript;
import yieldg.ui.YuiCanvas;
import yieldg.ui.YuiGraphical;

public class Mouse extends YieldScript implements MouseListener {

	@Override
	public String tag() {
		return "EngineMouse";
	}
	
	public static List<YuiCanvas> canvas = new ArrayList<>();

	private static double mouseX, mouseY;

	private static boolean clicking;

	private int framesclick;

	private boolean clickfalse;

	@Override
	public void tick() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mouseX = (b.getX() - (YieldApp.app().getWindow().getX() + YieldApp.app().getWindow().getInsets().left))
				* YieldApp.app().getWidth() / YieldApp.app().getW();
		mouseY = (b.getY() - (YieldApp.app().getWindow().getY() + (YieldApp.app().getWindow().getInsets().top)))
				* YieldApp.app().getHeight() / YieldApp.app().getH();
		if (clicking) {
			for (int i = 0; i < canvas.size(); i++) {
				YuiCanvas canvas1 = canvas.get(i);
				for (int i2 = 0; i2 < canvas1.getGraphicalList().size(); i2++) {
					YuiGraphical graphical = canvas1.getGraphicalList().get(i);
					graphical.onMouseClick();
				}
			}
			clickfalse = true;
		}
		if (clickfalse) {
			framesclick++;
		}
		if (framesclick > 0) {
			clickfalse = false;
			clicking = false;
			framesclick = 0;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicking = true;
	}

	public static boolean isTouching(double x, double y, double width, double height) {
		if ((getMouseX() > x && getMouseX() < x + width) && (getMouseY() > y && getMouseY() < y + height)) {
			return true;
		}
		return false;
	}

	public static double getMouseX() {
		return mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}

	public static boolean isClicking() {
		return clicking;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
