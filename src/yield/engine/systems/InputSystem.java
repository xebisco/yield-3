package yield.engine.systems;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import yield.engine.YieldApp;
import yield.engine.systems.main.YieldSystem;
import yield.util.Input;
import yieldg.ui.YuiCanvas;
import yieldg.ui.YuiGraphical;
import yieldg.util.YldWindow;

public class InputSystem extends YieldSystem implements KeyListener {

	private static List<Input> inputs = new ArrayList<>();
	
	public static List<YuiCanvas> canvas = new ArrayList<>();

	public InputSystem(YldWindow window) {
		YieldApp.app().addKeyListener(this);
		window.setInputSystem(this);
	}

	@Override
	public void tick() {
		for (int i = 0; i < InputSystem.getInputs().size(); i++) {
			YieldApp.app().getWindow().getInputSystem();
			Input input = InputSystem.getInputs().get(i);
			if (input.isClicked() || input.isInvertedClicked()) {
				if (input.getCf() == 0) {
					input.setClicked(false);
					input.setInvertedClicked(false);
				}
				input.setCf(input.getCf() + 1);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < inputs.size(); i++) {
			Input input = inputs.get(i);
			if (e.getKeyCode() == input.getKey()) {
				input.setPressed(true);
				input.setCf(0);
				input.setClicked(true);
				for (int i2 = 0; i2 < canvas.size(); i2++) {
					YuiCanvas canvas1 = canvas.get(i2);
					for (int i3 = 0; i3 < canvas1.getGraphicalList().size(); i3++) {
						YuiGraphical graphical = canvas1.getGraphicalList().get(i3);
						graphical.onKeyClick(e);
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < inputs.size(); i++) {
			Input input = inputs.get(i);
			if (e.getKeyCode() == input.getKey()) {
				input.setPressed(false);
				input.setCf(0);
				input.setInvertedClicked(true);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static List<Input> getInputs() {
		return inputs;
	}

	public static void setInputs(List<Input> inputs) {
		InputSystem.inputs = inputs;
	}
}
