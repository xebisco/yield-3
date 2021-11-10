package yield.core.engines;

import yield.core.engines.interfaces.YldLogical;
import yield.exceptions.YldLogicException;

/**
 * Essa engine serve para criar um game loop e enviar para um YldLogical.
 */
public final class YldLogicEngine implements Runnable {

	public final static String LOGIC_ENGINE_VERSION = "2b";

	private YldGraphicsEngine graphicsEngine;
	private boolean running = true;
	private double targetTPS = 60, TPS = targetTPS;
	private YldLogical yldLogical;
	private int ticks;
	private Thread thread;

	public YldLogicEngine(YldLogical yldLogical) {
		this.yldLogical = yldLogical;
		thread = new Thread(this);
		thread.setName("YieldThread");
		thread.start();
	}

	@Override
	public void run() {
		long initialTime = System.nanoTime();
		double timeT = 0;
		int ticks = 0;
		double deltaT = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			try {
				timeT = 1000000000d / targetTPS;

				long currentTime = System.nanoTime();
				deltaT += (currentTime - initialTime) / timeT;
				initialTime = currentTime;

				if (deltaT >= 1) {
					yldLogical.update();
					if (graphicsEngine != null)
						graphicsEngine.render();
					ticks++;
					this.ticks++;
					deltaT--;
				}

				if (System.currentTimeMillis() - timer > 1000d) {
					TPS = ticks;
					ticks = 0;
					timer += 1000;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new YldLogicException(e.getMessage());
			}

		}
	}

	public static String getLogicEngineVersion() {
		return LOGIC_ENGINE_VERSION;
	}

	public YldGraphicsEngine getGraphicsEngine() {
		return graphicsEngine;
	}

	public void setGraphicsEngine(YldGraphicsEngine graphicsEngine) {
		this.graphicsEngine = graphicsEngine;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
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
	 * @return the targetTPS
	 */
	public double getTargetTPS() {
		return targetTPS;
	}

	/**
	 * @param targetTPS the targetTPS to set
	 */
	public void setTargetTPS(double targetTPS) {
		this.targetTPS = targetTPS;
	}

	/**
	 * @return the tPS
	 */
	public double getTPS() {
		return TPS;
	}

	/**
	 * @param tPS the tPS to set
	 */
	public void setTPS(double tPS) {
		TPS = tPS;
	}

	/**
	 * @return the yldLogical
	 */
	public YldLogical getYldLogical() {
		return yldLogical;
	}

	/**
	 * @param yldLogical the yldLogical to set
	 */
	public void setYldLogical(YldLogical yldLogical) {
		this.yldLogical = yldLogical;
	}

	/**
	 * @return the ticks
	 */
	public int getTicks() {
		return ticks;
	}

	/**
	 * @param ticks the ticks to set
	 */
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
}
