package engine.yield;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import basic.Settings;
import basic.Time;
import basic.Yield;
import util.Input;
import util.file.wav.Audio;
import util.window.Window;

public class YieldMain {

	// janela do jogo
	private static Window window;

	// controlador da janela do jogo
	private static ScreenMain screenMain;
	
	//se o painel da yield está aberto
	private static boolean yieldPanel = false;
	
	private static Audio errorSound;

	// todas as classes que extendem da classe Yield
	private static List<Yield> objects = new ArrayList<Yield>();

	// os erros que são enviados para a engine
	private static List<String> errors = new ArrayList<String>();
	
	private static String version = "1.0_2";
	
	private boolean c;
	
	private Input alt, m;

	// Getters e setters >

	public static List<Yield> getObjects() {
		return objects;
	}

	public static void setObjects(List<Yield> objects) {
		YieldMain.objects = objects;
	}

	public static List<String> getErrors() {
		return errors;
	}

	public static Window getWindow() {
		return window;
	}
	
	public static ScreenMain getScreenMain() {
		return screenMain;
	}
	public static boolean getYieldPanel() {
		return yieldPanel;
	}
	public static String getVersion() {
		return version;
	}
	
	public static void setYieldPanel(boolean yieldPanel) {
		YieldMain.yieldPanel = yieldPanel;
	}
	

	// Getters e setters <
	
	public YieldMain() {
		
		errorSound = new Audio("/engine/resourses/error.wav");
		
		errorSound.setCustomVolume(true);
		
		errorSound.setVolume(100);
		
		screenMain = new ScreenMain();
		
		// Criar a janela do jogo
		window = new Window(Settings.getWindow_WIDTH(), Settings.getWindow_HEIGHT(), screenMain);
		
		window.exitOnClose();

		window.setTitle(Settings.getGame_NAME() + " " + Settings.getGame_VERSION());
		 	
		window.changeIcon(Settings.getIcon_PATH());
		if(Settings.isFULLSCREEN()) {
			window.fullscreen(true);
		} else {
			window.fullscreen(false);
		}

		new Loop();
		
		new Time();
		
		if(Settings.canOpenPanel()) {
			alt = new Input(KeyEvent.VK_ALT);
			m = new Input(KeyEvent.VK_M);
		}

	}
	
	public void Update() {
		
		if(YieldMain.getErrors().size() > 0) {
			yieldPanel = true;
		}
		
		if(alt.isPressed() && m.isPressed()) {
			if(c) {
				c = false;
				if(yieldPanel) {
					yieldPanel = false;
				} else {
					yieldPanel = true;
				}
			}
		} else {
			c = true;
		}
	}

	// Métodos principais >

	public static void forceStop() {
		window.setTitle("stopped...");	
		ScreenMain.closeWindow();
		Loop.stopRunning();
		try {
			Loop.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sdc() {
		Loop.stopRunning();
		try {
			Loop.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void playErrorSound() {
		errorSound.play(false);
	}

	// Métodos principais <

}
