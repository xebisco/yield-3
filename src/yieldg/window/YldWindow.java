package yieldg.window;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import yieldg.YldSprite;

/**
 * Uma implementação do JFrame feita para a Yield.
 */
public class YldWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private Dimension previousSize;
	private Point previousPoint;
	private boolean fullscreen;

	public YldWindow() {
		super();
		changeIcon("yieldlogo");
		setTitle("yield Window");
	}

	public void toFullscreen() {
		fullscreen = true;
		previousPoint = getLocation();
		setPreviousSize(getSize());
		dispose();
		setUndecorated(true);
		pack();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setVisible(true);
	}
	public void exitFullscreen() {
		fullscreen = false;
		dispose();
		setUndecorated(false);
		pack();
		setSize(previousSize);
		setLocation(previousPoint);
		setResizable(true);
		setVisible(true);
	}

	public void changeIcon(String path) {
		YldSprite icon = new YldSprite(path);

		Image iconImage = icon.getBufferedImage();

		this.setIconImage(iconImage);
	}

	public Dimension getPreviousSize() {
		return previousSize;
	}

	public void setPreviousSize(Dimension previousSize) {
		this.previousSize = previousSize;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Point getPreviousPoint() {
		return previousPoint;
	}

	public void setPreviousPoint(Point previousPoint) {
		this.previousPoint = previousPoint;
	}

}
