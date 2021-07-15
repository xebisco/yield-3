package util;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import basic.Settings;
import basic.Yield;
import engine.yield.YieldMain;

public class Input extends Yield implements KeyListener, MouseListener, MouseWheelListener {

	// map new key

	// is pressed or not
	private boolean isPressed;
	private boolean main;
	private static double mousex, mousey;
	private int key;
	public static List<Input> inputs = new ArrayList<Input>();
	private static boolean click = false;
	private static int clickf;
	private static boolean mwu;
	private static boolean mwd;

	// Getters e setters >

	public boolean isPressed() {
		return isPressed;
	}
	public void changeIsPressed(boolean is) {
		this.isPressed = is;
	}

	public int getKey() {
		return key;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public static double getMouseX() {
		return mousex;
	}

	public static double getMouseY() {
		return mousey;
	}

	// Getters e setters <

	public Input(int key) {
		if (key != 69696969) {
			this.key = key;
			inputs.add(this);
		} else {
			main = true;
		}
	}

	public void Update() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mousex = b.getX() / Settings.getWindow_WIDTH();
		mousey = b.getY() / Settings.getWindow_HEIGHT();
		if (click) {
			clickf++;
			if (clickf > 10)
				click = false;
		} else {
			clickf = 0;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (main) {
			for (int i = 0; i < inputs.size(); i++) {

				if (e.getKeyCode() == inputs.get(i).getKey()) {
					inputs.get(i).isPressed = true;
				}

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (main) {
			for (int i = 0; i < inputs.size(); i++) {

				if (e.getKeyCode() == inputs.get(i).getKey()) {
					inputs.get(i).isPressed = false;
				}

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
		clickf = 0;
	}

	public static void MouseUpdate() {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if (notches < 0) {
			// up
			mwu = true;
		} else {
			// down
			mwd = true;
		}

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

	public static boolean isMouseWheelDown() {
		if (mwd) {
			mwd = false;
			return true;
		}
		return false;
	}

	public static boolean isMouseWheelUp() {
		if (mwu) {
			mwu = false;
			return true;
		}
		return false;
	}

	public static int MouseX() {
		return (int) mousex;
	}

	public static int MouseY() {
		return (int) mousey;
	}

	public static boolean isClicked() {
		if (click) {
			click = false;
			return true;
		}
		return false;
	}
	

	public static boolean isTouching(Yield obj) {
		int windowx = (YieldMain.getWindow().getFrame().getX() + 8) / Settings.getWindow_WIDTH();
		int windowy = (YieldMain.getWindow().getFrame().getY() + 28) / Settings.getWindow_HEIGHT();

		int mousex = (int) ((obj.getX()) + windowx);
		int mousey = (int) ((obj.getY()) + windowy);

		int maxX = mousex + (obj.getWidth());
		int maxY = mousey + (obj.getHeight());

		if (mousex >= mousex && mousex <= maxX && mousey >= mousey && mousey <= maxY) {
			return true;
		}
		return false;

	}

	public static boolean isTouching(double mousex, double mousey, int width, int height) {
		int windowx = YieldMain.getWindow().getFrame().getX() + 8;
		int windowy = YieldMain.getWindow().getFrame().getY() + 28;

		int ix = (int) ((mousex) + windowx);
		int iy = (int) ((mousey) + windowy);

		int maxX = ix + (width);
		int maxY = iy + (height);

		if (mousex >= ix && mousex <= maxX && mousey >= iy && mousey <= maxY) {
			return true;
		}
		return false;
	}

}
