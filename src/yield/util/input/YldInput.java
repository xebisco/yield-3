package yield.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Essa classe contém todas as informações sobre o input do teclado da janela do jogo
 */
public final class YldInput implements KeyListener {

	private static Set<String> keysSet;

	public YldInput() {
		keysSet = new HashSet<String>();
		new YldMainInputs();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysSet.add(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysSet.remove(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * @return the keysSet
	 */
	public static Set<String> getKeysSet() {
		return keysSet;
	}

	public static boolean isKeyPressed(String key) {
		String keyToCompaire = key.toUpperCase();
		for (String string : keysSet) {
			try {
				if (string.hashCode() == keyToCompaire.hashCode()) {
					if (string.equals(keyToCompaire)) {
						return true;
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * @param keysSet the keysSet to set
	 */
	public static void setKeysSet(Set<String> keysSet) {
		YldInput.keysSet = keysSet;
	}

}
