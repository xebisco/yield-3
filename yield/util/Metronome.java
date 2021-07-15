package util;

import java.util.concurrent.atomic.AtomicBoolean;

import basic.Yield;
import engine.yield.YieldMain;

public class Metronome implements Runnable{
	Thread t;
	private AtomicBoolean keepRunning;
	public double bpm = 120;

	public Metronome(int bpm) {
		this.bpm = bpm;
		keepRunning = new AtomicBoolean(true);
		
		if(t == null) {
			t = new Thread(this);
		}
		t.start();
	}
	
	public void changeBpm(int bpm) {
		this.bpm = bpm;
	}

	public void end() {
		keepRunning.set(false);
	}
	public void start() {
		keepRunning.set(true);
	}

	@Override
	public void run() {
		while (keepRunning.get()) {
			for (int i = 0; i < YieldMain.getObjects().size(); i++) {
				Yield obj = YieldMain.getObjects().get(i);
				obj.BPMUpdate();
			}

			try {
				Thread.sleep((long) (1000 * (60.0 / (bpm * 4))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}