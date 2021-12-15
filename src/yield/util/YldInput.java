package yield.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public final class YldInput implements KeyListener {

    private static Set<String> keysSet;

    public YldInput() {
        keysSet = new HashSet<>();
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

        for (String s : keys) {
            String keyToCompaire = s.toUpperCase();
            for (String string : keysSet) {
                try {
                    if (string.hashCode() == keyToCompaire.hashCode()) {
                        if (string.equals(keyToCompaire)) {
                            all++;
                        }
                    }
                } catch (Exception ignore) {
                }
            }
        }
        if (key.endsWith(";")) {
            return all >= keys.length;
        } else return all != 0;
    }

    /**
     * @param keysSet the keysSet to set
     */
    public static void setKeysSet(Set<String> keysSet) {
        YldInput.keysSet = keysSet;
    }

}
