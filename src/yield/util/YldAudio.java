package yield.util;

import yield.objects.YldB;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class YldAudio extends YldB {
	
	private static float masterVolume = 90f;
	private final String path;
	private Clip clip;
	private boolean customVolume = false;
	private int frames;

	// Getters e setters >

	public static float getMasterVolume() {
		return masterVolume;
	}

	public static void setMasterVolume(float masterVolume) {
		YldAudio.masterVolume = masterVolume;
	}

	public boolean isCustomVolume() {
		return customVolume;
	}

	public void setCustomVolume(boolean customVolume) {
		this.customVolume = customVolume;
	}

	public String getPath() {
		return path;
	}

	public Clip getClip() {
		return clip;
	}

	// Getters e setters <

	public YldAudio(String path) {
		this.path = path;

		try {
			if (this.getClass().getResource(path) != null) {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(Objects.requireNonNull(this.getClass().getResource(path)));
				clip = AudioSystem.getClip();
				clip.open(audioIn);
			} else
				System.out.println("Audio: Cannot find " + path);
		} catch (UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Audio: Cannot find " + path);
		}

		load();

	}

	@Override
	public void create() {

	}

	@Override
	public void update() {
		frames++;
		if (!customVolume && clip != null && frames > 10) {
			setVolume(masterVolume);
		}
	}

	public float getVolume() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public void setVolume(float volume) {
		float actvolume = volume / 100f;
		if (actvolume < 0f || actvolume > 1f)
			throw new IllegalArgumentException("Volume not valid: " + actvolume);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(actvolume));
	}

	public void play() {
		boolean wait = false;
		if (clip == null) {
			return;
		}
		clip.isRunning();
		if (!wait) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void play(boolean wait) {
		if (clip == null) {
			return;
		}
		if (!(clip.isRunning()) && wait) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		} else if (!wait) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void play(boolean wait, boolean gotobegg) {
		if (clip == null) {
			return;
		}
		if (!(clip.isRunning()) && wait) {
			stop();
			if (gotobegg)
				clip.setFramePosition(0);
			clip.start();
		} else if (!wait) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void stop() {
		if (clip.isRunning())
			clip.stop();
	}

	public void close() {
		stop();
		clip.close();
	}
}
