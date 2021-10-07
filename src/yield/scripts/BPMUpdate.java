package yield.scripts;

import yield.main.Room;
import yield.main.Script;

public class BPMUpdate extends Script implements Runnable {

	@Override
	public String tag() {
		return "BPMUpdate";
	}

	private Thread thread;
	private int bpm;
	private boolean active;

	@Override
	public void start() {
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
				for (int i = 0; i < getBody().getScriptList().size(); i++) {
					Script script = getBody().getScriptList().get(i);
					if (Room.getActRoom().hashCode() == getBody().getRoom().hashCode())
						script.tickBPM();
				}
				Thread.sleep(60000 / bpm);
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

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

}
