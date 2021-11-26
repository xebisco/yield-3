package yieldg.ui.canvas.input;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import yield.YldApp;
import yield.YldScript;

/**
 * Essa classe é utilizada para localizar e obter ações do ponteiro do mouse um
 * uma janela.
 */
public class YldCanvasMouse extends YldScript implements MouseListener, MouseWheelListener {

	@Override
	public String tag() {
		return "YldCanvasMouse";
	}

	private double mouseX, mouseY;

	private boolean clicking, pressing;

	private int wheel;

	private Dimension resolution = new Dimension();

	private int framesclick;

	private boolean clickfalse;

	@Override
	public void tick() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mouseX = (b.getX()
				- (YldApp.window.getX() + YldApp.window.getInsets().left + yld.getYldGraphicsEngine().getXt()))
				* resolution.width / YldApp.core.getYldGraphicsEngine().getW();
		mouseY = (b.getY() - (YldApp.window.getY() + (YldApp.window.getInsets().top))) * resolution.height
				/ YldApp.core.getYldGraphicsEngine().getH();
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

	public boolean isTouching(double x, double y, double width, double height) {
		if ((getMouseX() > x && getMouseX() < x + width) && (getMouseY() > y && getMouseY() < y + height)) {
			return true;
		}
		return false;
	}

	public double getMouseX() {
		return mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public boolean isClicking() {
		return clicking;
	}

	/**
	 * @param mouseX the mouseX to set
	 */
	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	/**
	 * @param mouseY the mouseY to set
	 */
	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}

	/**
	 * @param clicking the clicking to set
	 */
	public void setClicking(boolean clicking) {
		this.clicking = clicking;
	}

	/**
	 * @return the wheel
	 */
	public int getWheel() {
		return wheel;
	}

	/**
	 * @param wheel the wheel to set
	 */
	public void setWheel(int wheel) {
		this.wheel = wheel;
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
	public boolean isPressing() {
		return pressing;
	}

	/**
	 * @param pressing the pressing to set
	 */
	public void setPressing(boolean pressing) {
		this.pressing = pressing;
	}

	public Dimension getResolution() {
		return resolution;
	}

	public void setResolution(Dimension resolution) {
		this.resolution = resolution;
	}

}
