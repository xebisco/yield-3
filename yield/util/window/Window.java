package util.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import basic.Yield;
import util.Input;
import util.Nothing;

public class Window extends Yield{

	private JFrame frame;
	
	private boolean isFullscreen;
	
	private Dimension previousRes;

	public Window(int window_WIDTH, int window_HEIGHT, Component comp) {
		frame = new JFrame();
		frame.add(new Nothing());
		if(comp != null)
		frame.add(comp);
		frame.setTitle("yield-default");
		frame.setResizable(false);
		frame.getContentPane().add(comp);
		previousRes = new Dimension(window_WIDTH, window_HEIGHT);
 
		do {
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setSize(new Dimension(previousRes.width + 7, previousRes.height + 35));
			frame.setLocationRelativeTo(null);
		} while (frame.getWidth() <= 0 || frame.getHeight() <= 0);
		frame.setVisible(true);
		
		//frame.requestFocus();
	}
	
	public void fullscreen(boolean is) {
		for(int i = 0; i < Input.inputs.size(); i++) {
			Input.inputs.get(i).changeIsPressed(false);
		}
		isFullscreen = is;
		frame.setVisible(false);
		frame.dispose();
		do {
			if (is) {
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				frame.setUndecorated(true);
				frame.setAlwaysOnTop(true);
			} else {
				frame.setSize(previousRes.width + 7, previousRes.height + 35);
				frame.setUndecorated(false);
				frame.setAlwaysOnTop(false);
			}
			frame.setLocationRelativeTo(null);
		} while (frame.getWidth() <= 0 || frame.getHeight() <= 0);
		frame.setVisible(true);
	}
	
	public void exitOnClose() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setTitle(String title) {
		frame.setVisible(false);
		frame.setTitle(title);
		frame.setVisible(true);
	}
	
	public void changeResolution(Dimension dimension) {
		for(int i = 0; i < Input.inputs.size(); i++) {
			Input.inputs.get(i).changeIsPressed(false);
		}
		frame.dispose();
		do {
			frame.setUndecorated(false);
			frame.setAlwaysOnTop(false);
			frame.setSize(dimension.width + 7 , dimension.height + 29);
			frame.setLocationRelativeTo(null);
		} while (frame.getWidth() <= 0 || frame.getHeight() <= 0);
		frame.setVisible(true);
		frame.pack();
	}
	
	//Getters and setters >
	
	public JFrame getFrame() {
		return frame;
	}

	public double getX() {
		return frame.getX();
	}

	public double getY() {
		return frame.getY();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}
	
	public boolean isFullscreen() {
		return isFullscreen;
	}
	
	//Getters and setters <
	
	public void Update() {
		setX(frame.getX());
		setY(frame.getY());
		setWidth(frame.getX());
		setHeight(frame.getY());
	}
	
	public void changeIcon(String path) {
		Image image = null;
		try {
				image = ImageIO.read(Yield.getMainClass().getClass().getResource(path));
				
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setIconImage(image);
	}

}
