package yield.core.engines;

import yield.core.engines.interfaces.YldBPM;

public final class YldBPMEngine implements Runnable {

	public final static String BPM_ENGINE_VERSION = "1.0";

	private Thread thread;
	private double bpm;
	private boolean running, pause;
	private YldBPM yldBpm;

	public YldBPMEngine(YldBPM yldBPM) {
		this.yldBpm = yldBPM;
		thread = new Thread(this);
		thread.setName("BPMYieldThread");
		thread.start();
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			if (getBpm() == 0)
				setBpm(120);
			try {
				if (yldBpm != null && !pause) {
					yldBpm.tickBPM();
				}
				Thread.sleep((long) (60000.0 / bpm));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
	 * @return the yldBpm
	 */
	public YldBPM getYldBpm() {
		return yldBpm;
	}

	/**
	 * @param yldBpm the yldBpm to set
	 */
	public void setYldBpm(YldBPM yldBpm) {
		this.yldBpm = yldBpm;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public double getBpm() {
		return bpm;
	}

	public void setBpm(double bpm) {
		this.bpm = bpm;
	}

}
