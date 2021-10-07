package yield.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import yield.engine.systems.InputSystem;
import yield.engine.systems.main.SYS;
import yield.main.Body;
import yield.main.Room;
import yield.main.Script;
import yield.main.YieldScript;
import yield.util.Audio;
import yield.util.Mouse;
import yield.util.Time;
import yieldg.util.YldWindow;

public final class YieldApp extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String ENGINEVERSION_STRING = "2";

	private int TPS, RPS, ticks, frames, width = 1280, height = 720, numBuffers = 1;

	private Color bgColor;

	private boolean print = true, showOverlay, refreshBuffers, running;

	private boolean pause;

	double h, w;

	private Thread thread;

	private YldWindow window;

	private static YieldApp mainYieldApp;

	private List<Room> rooms;

	private List<YieldScript> yieldScripts;

	private Mouse mouse;

	private Audio closeYieldAudio;

	private Graphics mainGraphics;

	public YieldApp() {
		Locale.setDefault(Locale.US);

		setBgColor(Color.black);
		if (app() == null) {
			setMainYieldApp(this);
		}
		if (getRooms() == null) {
			setRooms(new ArrayList<>());
		}
		if (getYieldScripts() == null) {
			setYieldScripts(new ArrayList<>());
		}
		if (getThread() == null) {
			setThread(new Thread(this));
		}

		new SYS();

		Room.defaultRoom = new Room("default");
	}

	public void start(int width, int height) {
		Room.setActRoom(Room.defaultRoom);
		getYieldScripts().add(new Time());
		setWidth(width);
		setHeight(height);
		setWindow(new YldWindow());
		if (getTPS() == 0) {
			setTPS(30);
		}
		if (getRPS() == 0) {
			setRPS(getTPS());
		}
		getWindow().add(this);
		getWindow().setSize(width, height);
		// getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getWindow().setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		// getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
		// getWindow().setUndecorated(true);
		// getWindow().setResizable(false);

		getWindow().setVisible(true);
		getWindow().setSize(getWindow().getWidth(), height + getWindow().getInsets().top);
		getWindow().setLocationRelativeTo(null);
		setRunning(true);
		getThread().setName("YieldThread");
		getThread().start();

		SYS.startDefaultSystems();

		InputSystem inputSystem = new InputSystem(window);
		SYS.add(inputSystem);

		mouse = new Mouse();

		getYieldScripts().add(mouse);

		this.addMouseListener(mouse);

		closeYieldAudio = new Audio("/yieldclose.wav");

		// overlaySystem = new OverlaySystem();
	}

	public void tick() {
		requestFocus();
		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			if (Room.getActRoom().getName().equals(room.getName()))
				room.update();
		}

		for (int i = 0; i < yieldScripts.size(); i++) {
			YieldScript yieldScript = yieldScripts.get(i);
			processScript(yieldScript);
			if (yieldScript.getLayer() >= 0) {
				Collections.swap(yieldScripts, yieldScript.getLayer(), i);
				yieldScript.setLayer(-1);
			}
		}

		for (int i = 0; i < SYS.getSystems().size(); i++) {
			if (SYS.getSystems().get(i) != null)
				SYS.getSystems().get(i).tick();
		}

		if (!window.isVisible()) {
			setRunning(false);
		}

		/*
		 * for (int i = 0; i < scripts.size(); i++) { Script s = scripts.get(i); if
		 * (s.getLayer() >= 0) { Collections.swap(scripts, s.getLayer(), i);
		 * s.setLayer(-1); } s.update(); if (s.getFrames() > 1) { if (s.getMyRoom() ==
		 * null) { s.tick(); } else if (s.getMyRoom().equals(Script.getActRoom())) {
		 * s.tick(); } } if (s.getFrames() == 1) { s.start(); } }
		 */
	}

	public void render() {
		h = ((double) getWindow().getHeight()) - (getWindow().getInsets().top + getWindow().getInsets().right);
		w = (h * ((double) getWidth() / (double) getHeight()));
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null || refreshBuffers) {
			refreshBuffers = false;
			this.createBufferStrategy(numBuffers);
			return;
		}

		Graphics g = image.getGraphics();
		g.setColor(getBgColor());
		g.fillRect(0, 0, width, height);

		Graphics2D g2 = (Graphics2D) g;

		AffineTransform oldXForm = g2.getTransform();

		//////////////////////////////////////////////////////////////////////////////////

		/*
		 * if (scripts.size() == 0 || (scripts.size() == 1 && scripts.get(0) instanceof
		 * Time)) { g.setColor(Color.white); g.setFont(Font.getFont("arial"));
		 * g.drawString("no scripts added in Yield.", 0, g.getFont().getSize()); }
		 * 
		 * for (int i = 0; i < scripts.size(); i++) { Script s = scripts.get(i); if
		 * (s.getMyRoom() == null) { s.render(g); } else if
		 * (s.getMyRoom().equals(Script.getActRoom())) { s.render(g); } }
		 */

		setMainGraphics(g);

		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			if (Room.getActRoom().getName().equals(room.getName()))
				room.renderer(g);
		}

		for (int i = 0; i < yieldScripts.size(); i++) {
			YieldScript yieldScript = yieldScripts.get(i);
			processScript(yieldScript, g);
		}

		///////////////////////////////////////////////////////////////////////////////////
		g.dispose();
		g = bs.getDrawGraphics();
		if (!pause && window.isActive()) {
			g.drawImage(image, 0, 0, (int) w, (int) h, null);
		}

		g2.transform(oldXForm);

		bs.show();
	}

	public void processScript(Script s, Graphics g) {
		if (s.getFrames() > 2 && s.isRender())
			s.render(g);
	}

	public void processScript(Script s) {
		// int toM;

		s.update();
		if (s.getFrames() > 1) {
			s.tick();
		}
		if (s.getFrames() == 1) {
			s.start();
		}
	}

	/*
	 * public void processYieldScript(YieldScript s, Graphics g) { if (s.getFrames()
	 * > 2) s.render(g); }
	 * 
	 * public void processYieldScript(YieldScript s) { s.update(); if (s.getFrames()
	 * > 1) { s.tick(); } if (s.getFrames() == 1) { s.start(); } }
	 */

	public void processBody(Body b) {
		b.update();
	}

	public void processBody(Body b, Graphics g) {
		b.renderer(g);
	}

	@Override
	public void run() {
		long initialTime = System.nanoTime();
		double timeT = 0;
		double timeF = 0;
		int ticks = 0, frames = 0;
		double deltaT = 0, deltaF = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			timeT = 1000000000 / getTPS();
			timeF = 1000000000 / getRPS();

			long currentTime = System.nanoTime();
			deltaT += (currentTime - initialTime) / timeT;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaT >= 1) {
				this.tick();
				ticks += 1;
				this.ticks++;
				deltaT--;
			}

			if (deltaF >= 1) {
				if (getWindow().getWidth() != 0 || getWindow().getHeight() != 0)
					this.render();
				frames += 1;
				this.frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 500) {
				if (print) {
					System.out.println(String.format("TPS: %s, RPS: %s", ticks, frames));
				}
				Time.setSlowRenderPS(frames);
				Time.setSlowTickPS(ticks);
				// Time.getRPSs().add(frames);
				// Time.getTPSs().add(ticks);
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
		if (closeYieldAudio != null)
			closeYieldAudio.play();
		getWindow().dispose();
	}

	public static YieldApp app() {
		return mainYieldApp;
	}

	public static void setMainYieldApp(YieldApp mainYieldApp) {
		YieldApp.mainYieldApp = mainYieldApp;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public int getTPS() {
		return TPS;
	}

	public void setTPS(int tPS) {
		TPS = tPS;
	}

	public int getRPS() {
		return RPS;
	}

	public void setRPS(int rPS) {
		RPS = rPS;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumBuffers() {
		return numBuffers;
	}

	public void setNumBuffers(int numBuffers) {
		this.numBuffers = numBuffers;
	}

	public boolean isShowOverlay() {
		return showOverlay;
	}

	public void setShowOverlay(boolean showOverlay) {
		this.showOverlay = showOverlay;
	}

	public YldWindow getWindow() {
		return window;
	}

	public void setWindow(YldWindow window) {
		this.window = window;
	}

	public boolean isRefreshBuffers() {
		return refreshBuffers;
	}

	public void setRefreshBuffers(boolean refreshBuffers) {
		this.refreshBuffers = refreshBuffers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getH() {
		return h;
	}

	public double getW() {
		return w;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public static String getEngineversionString() {
		return ENGINEVERSION_STRING;
	}

	public void setH(double h) {
		this.h = h;
	}

	public void setW(double w) {
		this.w = w;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void setRooms(Room room) {
		rooms.add(room);
	}

	public List<YieldScript> getYieldScripts() {
		return yieldScripts;
	}

	public void setYieldScripts(List<YieldScript> yieldScripts) {
		this.yieldScripts = yieldScripts;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public static YieldApp getMainYieldApp() {
		return mainYieldApp;
	}

	public Audio getCloseYieldAudio() {
		return closeYieldAudio;
	}

	public void setCloseYieldAudio(Audio closeYieldAudio) {
		this.closeYieldAudio = closeYieldAudio;
	}

	public Graphics getMainGraphics() {
		return mainGraphics;
	}

	public void setMainGraphics(Graphics mainGraphics) {
		this.mainGraphics = mainGraphics;
	}
}
