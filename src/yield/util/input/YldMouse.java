package yield.util.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import yield.YldApp;
import yield.YldScript;

public class YldMouse extends YldScript implements MouseListener, MouseWheelListener {

	@Override
	public String tag() {
		return "EngineMouse";
	}

	private static double mouseX, mouseY;

	private static boolean clicking, pressing;

	private static int wheel;

	private int framesclick;

	private boolean clickfalse;

	@Override
	public void tick() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mouseX = (b.getX() - (YldApp.window.getX() + YldApp.window.getInsets().left))
				* YldApp.core.getYldGraphicsEngine().getWWidth() / YldApp.core.getYldGraphicsEngine().getW();
		mouseY = (b.getY() - (YldApp.window.getY() + (YldApp.window.getInsets().top)))
				* YldApp.core.getYldGraphicsEngine().getWHeight() / YldApp.core.getYldGraphicsEngine().getH();
		if (clicking) {
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheel = e.getWheelRotation();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressing = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressing = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

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
	 * @param mouseX the mouseX to set
	 */
	public static void setMouseX(double mouseX) {
		YldMouse.mouseX = mouseX;
	}

	/**
	 * @param mouseY the mouseY to set
	 */
	public static void setMouseY(double mouseY) {
		YldMouse.mouseY = mouseY;
	}

	/**
	 * @param clicking the clicking to set
	 */
	public static void setClicking(boolean clicking) {
		YldMouse.clicking = clicking;
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
		YldMouse.wheel = wheel;
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

	/**
	 * @return the pressing
	 */
	public static boolean isPressing() {
		return pressing;
	}

	/**
	 * @param pressing the pressing to set
	 */
	public static void setPressing(boolean pressing) {
		YldMouse.pressing = pressing;
	}
}
