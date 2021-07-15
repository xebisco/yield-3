package engine.yield;

import java.awt.image.BufferedImage;

import basic.Settings;
import basic.Yield;
import init.Init;

public class Loop implements Runnable {

	public static Thread thread;

	// tempo que o Update() atualiza
	private double targetTime;

	// É aumentado a todo frame do Update()
	private static int totalFrames;

	// Se o jogo está rodando ou não
	private static boolean running;

	// Getters e setters >

	public double getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}

	public static int getTotalFrames() {
		return totalFrames;
	}

	public static void stopRunning() {
		running = false;
	}

	public static Thread getThread() {
		return thread;
	}

	// Getters e setters <

	public Loop() {
		targetTime = 1000 / (Settings.getGame_FPS());

		startUpdate();
	}

	public synchronized void startUpdate() {
		YieldMain.getScreenMain().setImage(new BufferedImage(Settings.getGame_WIDTH() - 16, Settings.getGame_HEIGHT(),
				BufferedImage.TYPE_INT_RGB));

		if (thread == null) {
			thread = new Thread(this);
		}
		running = true;
		thread.start();
	}

	public void Update() {
		if (YieldMain.getErrors().size() > 0) {
			System.out.println(YieldMain.getErrors());
		}

		for (int i = 0; i < YieldMain.getObjects().size(); i++) {
			Yield obj = YieldMain.getObjects().get(i);
			obj.Update();
		}

		if (Init.yieldMain != null) {
			Init.yieldMain.Update();
		}

		if (totalFrames > 5) {
			YieldMain.getScreenMain().render();
		}

	}

	// call update2
	@Override
	public void run() {
		long start;
		long elapsed;
		long wait = 0;

		// game loop
		while (running) {

			totalFrames++;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				running = false;
				e.printStackTrace();
			}

			start = System.nanoTime();

			Update();

			elapsed = System.nanoTime() - start;

			wait = (long) (targetTime - elapsed / 1000000);
			if (wait < 0)
				wait = 5;

		}
		YieldMain.sdc();
	}

}
