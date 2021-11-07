package yield.core.engines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import yield.core.engines.interfaces.YldGraphical;
import yieldg.window.YldWindow;

public final class YldGraphicsEngine extends JPanel {

	private static final long serialVersionUID = 1L;

	public final static String GRAPHICS_ENGINE_VERSION = "2";

	private boolean running = true, refreshBuffers = true, pause = false;
	private double targetFPS = 60, FPS = targetFPS, addX, addY, addWidth, addHeight;
	private YldGraphical yldGraphical;
	private int frames, width = 427, height = 240, numBuffers = 1, h, w, xt;
	private Thread thread;
	private Graphics2D graphics2d;
	private YldWindow window;

	public YldGraphicsEngine(YldGraphical yldGraphical) {
		this.yldGraphical = yldGraphical;
	}

	BufferedImage image;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (window != null) {

			g.setColor(Color.black);
			g.fillRect(0, 0, window.getWidth(), window.getHeight());

			requestFocus();

			h = getWHeight() + (window.getHeight() - getWHeight()) - window.getInsets().top - window.getInsets().left;
			w = (int) (h * ((double) getWWidth() / (double) getWHeight()));
			if (window.isFullscreen()) {
				xt = window.getWidth() / 2 - w / 2;
			} else {
				xt = 0;
			}

			if (image == null || image.getWidth() != width || image.getHeight() != height) {
				image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			}

			image.setAccelerationPriority(1);

			Graphics2D gii = image.createGraphics();

			gii.setColor(Color.black);
			gii.fillRect(0, 0, width, height);

			//////////////////////////////////////////////////////////////////////////////////

			setGraphics2d(gii);

			yldGraphical.render(gii);

			///////////////////////////////////////////////////////////////////////////////////

			gii.dispose();

			if (!pause) {
				try {
					g.drawImage(image, (int) addX + xt, (int) addY, (int) (w + addWidth), (int) (h + addHeight), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			image.flush();

			// g.dispose();
		}
	}

	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * @return the targetFPS
	 */
	public double getTargetFPS() {
		return targetFPS;
	}

	/**
	 * @param targetFPS the targetFPS to set
	 */
	public void setTargetFPS(double targetFPS) {
		this.targetFPS = targetFPS;
	}

	/**
	 * @return the fPS
	 */
	public double getFPS() {
		return FPS;
	}

	/**
	 * @param fPS the fPS to set
	 */
	public void setFPS(double fPS) {
		FPS = fPS;
	}

	/**
	 * @return the yldGraphical
	 */
	public YldGraphical getYldGraphical() {
		return yldGraphical;
	}

	/**
	 * @param yldGraphical the yldGraphical to set
	 */
	public void setYldGraphical(YldGraphical yldGraphical) {
		this.yldGraphical = yldGraphical;
	}

	/**
	 * @return the frames
	 */
	public int getFrames() {
		return frames;
	}

	/**
	 * @param frames the frames to set
	 */
	public void setFrames(int frames) {
		this.frames = frames;
	}

	/**
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/**
	 * @return the graphics2d
	 */
	public Graphics2D getGraphics2d() {
		return graphics2d;
	}

	/**
	 * @param graphics2d the graphics2d to set
	 */
	public void setGraphics2d(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the width
	 */
	public int getWWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getWHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setWHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the refreshBuffers
	 */
	public boolean isRefreshBuffers() {
		return refreshBuffers;
	}

	/**
	 * @param refreshBuffers the refreshBuffers to set
	 */
	public void setRefreshBuffers(boolean refreshBuffers) {
		this.refreshBuffers = refreshBuffers;
	}

	/**
	 * @return the numBuffers
	 */
	public int getNumBuffers() {
		return numBuffers;
	}

	/**
	 * @param numBuffers the numBuffers to set
	 */
	public void setNumBuffers(int numBuffers) {
		this.numBuffers = numBuffers;
	}

	/**
	 * @return the window
	 */
	public YldWindow getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(YldWindow window) {
		this.window = window;
	}

	/**
	 * @return the addX
	 */
	public double getAddX() {
		return addX;
	}

	/**
	 * @param addX the addX to set
	 */
	public void setAddX(double addX) {
		this.addX = addX;
	}

	/**
	 * @return the addY
	 */
	public double getAddY() {
		return addY;
	}

	/**
	 * @param addY the addY to set
	 */
	public void setAddY(double addY) {
		this.addY = addY;
	}

	/**
	 * @return the addWidth
	 */
	public double getAddWidth() {
		return addWidth;
	}

	/**
	 * @param addWidth the addWidth to set
	 */
	public void setAddWidth(double addWidth) {
		this.addWidth = addWidth;
	}

	/**
	 * @return the addHeight
	 */
	public double getAddHeight() {
		return addHeight;
	}

	/**
	 * @param addHeight the addHeight to set
	 */
	public void setAddHeight(double addHeight) {
		this.addHeight = addHeight;
	}

	/**
	 * @return the graphicsEngineVersion
	 */
	public static String getGraphicsEngineVersion() {
		return GRAPHICS_ENGINE_VERSION;
	}

	/**
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}

	/**
	 * @param pause the pause to set
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
