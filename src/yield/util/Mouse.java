package yield.util;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import yield.engine.YieldApp;
import yield.main.YieldScript;
import yieldg.ui.YuiCanvas;
import yieldg.ui.YuiGraphical;

public class Mouse extends YieldScript implements MouseListener, MouseWheelListener {

	@Override
	public String tag() {
		return "EngineMouse";
	}
	
	public static List<YuiCanvas> canvas = new ArrayList<>();

	private static double mouseX, mouseY;

	private static boolean clicking;

	private static int wheel;

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

		/**
	 * @return the canvas
	 */
	public static List<YuiCanvas> getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public static void setCanvas(List<YuiCanvas> canvas) {
		Mouse.canvas = canvas;
	}

	/**
	 * @param mouseX the mouseX to set
	 */
	public static void setMouseX(double mouseX) {
		Mouse.mouseX = mouseX;
	}

	/**
	 * @param mouseY the mouseY to set
	 */
	public static void setMouseY(double mouseY) {
		Mouse.mouseY = mouseY;
	}

	/**
	 * @param clicking the clicking to set
	 */
	public static void setClicking(boolean clicking) {
		Mouse.clicking = clicking;
	}

	/**
	 * @return the wheel
	 */
	public static int getWheel() {
		return wheel;
	}

	/**
	 * @param wheel the wheel to set
	 */
	public static void setWheel(int wheel) {
		Mouse.wheel = wheel;
	}

	/**
	 * @return the framesclick
	 */
	public int getFramesclick() {
		return framesclick;
	}

	/**
	 * @param framesclick the framesclick to set
	 */
	public void setFramesclick(int framesclick) {
		this.framesclick = framesclick;
	}

	/**
	 * @return the clickfalse
	 */
	public boolean isClickfalse() {
		return clickfalse;
	}

	/**
	 * @param clickfalse the clickfalse to set
	 */
	public void setClickfalse(boolean clickfalse) {
		this.clickfalse = clickfalse;
	}

	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheel = e.getWheelRotation();
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
