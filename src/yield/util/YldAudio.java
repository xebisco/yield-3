package yield.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import yield.YldScript;

/**
 * Essa classe é utilizada para carregar e tocar arquivos de áudio.
 */
public class YldAudio extends YldScript {

	@Override
	public String tag() {
		return "YldAudio";
	}
	
	private static float masterVolume = 90f;
	private String path;
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

		AudioInputStream audioIn = null;
		try {
			if (this.getClass().getResource(path) != null) {
				audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
				clip = AudioSystem.getClip();
				clip.open(audioIn);
			} else
				System.out.println("Audio: Cannot find " + path);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Audio: Cannot find " + path);
		}

	}

	@Override
	public void tick() {
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
