package yield;

import java.awt.Dimension;
import java.util.Locale;

<<<<<<< HEAD
<<<<<<< HEAD
import mapp.MappMain;
=======
import jdk.jfr.Experimental;
>>>>>>> parent of a3bb3e3 (Back to intellij!)
=======
import jdk.jfr.Experimental;
>>>>>>> parent of a3bb3e3 (Back to intellij!)
import yield.core.YldCore;
import yield.object.YldObjectCore;
import yield.util.YldTime;
import yield.util.input.YldInput;
import yield.util.input.YldMouse;
import yieldg.ui.YieldUI;
import yieldg.window.YldWindow;

/**
 * O YldApp é a classe onde o jogo inicia, ele inicia o YldCore e todas as suas dependências
 */
public abstract class YldApp {

	public final static String YIELD_VERSION = "3.7.3";

	public static YldWindow window;
	public static YldScript windowScript;
	public static YldCore core, yld;
	protected static Dimension resolutionDimension, sizeDimension;
	public static Dimension actSize;
	public static YldObjectCore objectCore;
	private static boolean canWindowSwitchToFullscreen = true;
	@Experimental
	/**NOT FINISHED*/
	protected boolean startYieldUI = false;

	public YldApp() {
		Locale.setDefault(Locale.US);
		start();
	}

	/**
	 * Create YldCore class, and start it all.
	 */
	public void startYield() {
		System.out.println("Starting Yield " + YIELD_VERSION + "...");

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (resolutionDimension == null) {
			resolutionDimension = new Dimension(1280, 720);
		}

		if (sizeDimension == null) {
			sizeDimension = new Dimension(1280, 720);
		}

		System.out.println("\n-	-	-	-	-	-\n");
		System.out.println("Starting YldCore...");
		core = new YldCore();
		core.app = this;
		System.out.println("YldCore Started!");
		System.out.println("-----------///-----------");
		System.out.println("Starting Engines...");
		core.startEngines();
		System.out.println("Setting YldGraphicsEngine \nresolution as: width: " + resolutionDimension.width
				+ ", height " + resolutionDimension.height + ".");
		core.getYldGraphicsEngine().setWWidth(resolutionDimension.width);
		core.getYldGraphicsEngine().setWHeight(resolutionDimension.height);
		System.out.println("Engines Started!");
		System.out.println("-----------///-----------");
		System.out.println("Creating YldWindow...");
		window = new YldWindow();
		System.out.println("Setting YldWindow size \nas: width: " + sizeDimension.width + ", height "
				+ sizeDimension.height + ".");
		window.setSize(sizeDimension);
		window.setDefaultCloseOperation(YldWindow.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
		System.out.println("Applying YldWindow size...");
		window.setSize(sizeDimension.width, sizeDimension.height + window.getInsets().top);
		System.out.println("YldWindow has been created!");
		System.out.println("-----------///-----------");
		core.getYldGraphicsEngine().setWindow(window);
		window.add(core.getYldGraphicsEngine());
		System.out.println("YldWindow linked to YldCore!");
		System.out.println("-----------///-----------");
		YldMouse mouse = new YldMouse();
		core.getYldGraphicsEngine().addMouseListener(mouse);
		core.getYldGraphicsEngine().addMouseWheelListener(mouse);
		System.out.println("YldMouse linked to YldGraphicsEngine!");
		System.out.println("-----------///-----------");
		core.getYldGraphicsEngine().addKeyListener(new YldInput());
		System.out.println("YldInput linked to YldGraphicsEngine!");
		System.out.println("-----------///-----------");
		System.out.println("Creating windowScript...");
		windowScript = new YldScript() {

			boolean canPressF11;

			@Override
			public String tag() {
				return "windowScript";
			}

			@Override
			public void tick() {
				if (canWindowSwitchToFullscreen) {
					if (YldInput.isKeyPressed("F11")) {
						if (canPressF11) {
							canPressF11 = false;
							if (window.isFullscreen()) {
								window.exitFullscreen();
								window.setResizable(false);
							} else {
								window.toFullscreen();
								window.setResizable(false);
							}
						}
					} else {
						canPressF11 = true;
					}
				}
			}

		};
		System.out.println("windowScript created!");
		System.out.println("-----------///-----------");

		new YldTime();
		System.out.println("YldTime created!");
		core.getYldGraphicsEngine().setPause(false);
		/*System.out.println("Starting YldObjectCore...");
		objectCore = new YldObjectCore();
		System.out.println("-----------///-----------");
		System.out.println("YldObjectCore started!");*/
		yld = core;
		if(startYieldUI) {
			System.out.println("-----------///-----------");
			System.out.println("Starting YieldUI...");
			core.setYieldUI(new YieldUI());
			System.out.println("YieldUI started!");
		}
<<<<<<< HEAD
<<<<<<< HEAD
		System.out.println("-----------///-----------");
		System.out.println("Creating MappSystem...");
		MappMain.startMappSystem();
		System.out.println("MappSystem created!");
		System.out.println("Starting MappMain...");
		MappMain.startMappSystem();
		System.out.println("MappMain started!");
=======
>>>>>>> parent of a3bb3e3 (Back to intellij!)
=======
>>>>>>> parent of a3bb3e3 (Back to intellij!)
		System.out.println("\n-	-	-	-	-	-\n");
		System.out.println("Yield started!");
	}

	/**
	 * Called before the class constructor, recommended for assets and stuff
	 */
	public abstract void start();

	/**
	 * @return the canWindowSwitchToFullscreen
	 */
	public static boolean isCanWindowSwitchToFullscreen() {
		return canWindowSwitchToFullscreen;
	}

	/**
	 * @param canWindowSwitchToFullscreen the canWindowSwitchToFullscreen to set
	 */
	public static void setCanWindowSwitchToFullscreen(boolean canWindowSwitchToFullscreen) {
		YldApp.canWindowSwitchToFullscreen = canWindowSwitchToFullscreen;
	}

}
