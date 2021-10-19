package yield;

import java.awt.Dimension;
import java.util.Locale;

import yield.core.YldCore;
import yield.util.YldTime;
import yield.util.input.YldInput;
import yield.util.input.YldMouse;
import yieldg.window.YldWindow;

public abstract class YldApp {

	public static String yield_engine_version = "3";
	
	public static YldWindow window;
	public static YldCore core;
	protected static Dimension resolutionDimension, sizeDimension;
	public static Dimension actSize;

	public YldApp() {
		Locale.setDefault(Locale.US);
		start();
	}

	/**
	 * Create YldCore class, and start it all.
	 */
	public void startYield() {
		System.out.println("Starting YldCore...");
		core = new YldCore();
		System.out.println("YldCore Started!");
		System.out.println("-----------///-----------");
		System.out.println("Creating YldWindow...");
		if (resolutionDimension == null) {
			resolutionDimension = new Dimension(1280, 720);
		}

		if (sizeDimension == null) {
			sizeDimension = new Dimension(1280, 720);
		}

		window = new YldWindow();
		window.setSize(sizeDimension);
		window.setDefaultCloseOperation(YldWindow.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		// window.setResizable(false);
		window.setSize(sizeDimension.width, sizeDimension.height + window.getInsets().top);
		System.out.println("YldWindow has been created!");
		System.out.println("-----------///-----------");
		System.out.println("Starting Engines...");
		core.startEngines();
		core.getYldGraphicsEngine().setWWidth(resolutionDimension.width);
		core.getYldGraphicsEngine().setWHeight(resolutionDimension.height);
		System.out.println("Engines Started!");
		System.out.println("-----------///-----------");
		core.getYldGraphicsEngine().setWindow(window);
		window.add(core.getYldGraphicsEngine());
		System.out.println("YldWindow linked to YldCore!");
		System.out.println("-----------///-----------");
		core.getYldGraphicsEngine().setWindow(window);
		window.add(core.getYldGraphicsEngine());
		YldMouse mouse = new YldMouse();
		core.getYldGraphicsEngine().addMouseListener(mouse);
		core.getYldGraphicsEngine().addMouseWheelListener(mouse);
		System.out.println("YldMouse linked to YldGraphicsEngine!");
		System.out.println("-----------///-----------");
		core.getYldGraphicsEngine().addKeyListener(new YldInput());
		System.out.println("YldInput linked to YldGraphicsEngine!");
		System.out.println("-----------///-----------");
		new YldTime();
		System.out.println("YldTime created!");
	}

	/**
	 * Called before the class constructor, recommended for assets and stuff
	 */
	public abstract void start();

}
