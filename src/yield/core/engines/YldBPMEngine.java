package yield.core.engines;

import yield.core.engines.interfaces.YldBPM;

public final class YldBPMEngine implements Runnable {
	
	public final static String BPM_ENGINE_VERSION = "1.0";
	
	private Thread thread;
	private double bpm;
	private boolean active;
	private YldBPM yldBpm;

	public YldBPMEngine(YldBPM yldBPM) {
		this.yldBpm = yldBPM;
		thread = new Thread(this);
		thread.setName("BPMYieldThread");
		thread.start();
	}

	@Override
	public void run() {
		active = true;
		while (active) {
			if (getBpm() == 0)
				setBpm(120);
			try {
				if(yldBpm != null) {
					yldBpm.tickBPM();
				}
				Thread.sleep((long) (60000d / bpm));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
