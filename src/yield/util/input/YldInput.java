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

        String[] keys = key.split(":");
        int all = 0;
        if (keys[keys.length - 1].endsWith(";")) {
            keys[keys.length - 1] = keys[keys.length - 1].substring(0, keys[keys.length - 1].length() - 1);
        }

        for (int i = 0; i < keys.length; i++) {
            String keyToCompaire = keys[i].toUpperCase();
            for (String string : keysSet) {
                try {
                    if (string.hashCode() == keyToCompaire.hashCode()) {
                        if (string.equals(keyToCompaire)) {
                            all++;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (key.endsWith(";")) {
            if (all >= keys.length) {
                return true;
            }
        } else if (all != 0) {
            return true;
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
