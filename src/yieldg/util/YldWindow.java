package yieldg.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import yield.engine.YieldApp;
import yield.engine.systems.InputSystem;
import yieldg.main.Sprite;

public class YldWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private InputSystem inputSystem;
	private Dimension previousSize;
	private Point previousPoint;
	private boolean fullscreen;

	public YldWindow() {
		changeIcon("yieldlogo");
		setTitle("yield Window");
	}

	public void toFullscreen() {
		fullscreen = true;
		previousPoint = getLocation();
		YieldApp.app().setPause(true);
		setPreviousSize(getSize());
		dispose();
		setUndecorated(true);
		pack();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setVisible(true);
		YieldApp.app().setPause(false);
	}
	public void exitFullscreen() {
		fullscreen = false;
		YieldApp.app().setPause(true);
		dispose();
		setUndecorated(false);
		pack();
		setSize(previousSize);
		setLocation(previousPoint);
		setResizable(true);
		setVisible(true);
		YieldApp.app().setPause(false);
	}

	public void changeIcon(String path) {
		Sprite icon = new Sprite(path);

		Image iconImage = icon.getBufferedImage();

		this.setIconImage(iconImage);
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem inputSystem) {
		this.inputSystem = inputSystem;
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
