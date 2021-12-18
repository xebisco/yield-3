package yield.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public final class YldInput implements KeyListener {

    private static Set<Integer> keysSet;

    public YldInput() {
        keysSet = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysSet.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysSet.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @return the keysSet
     */
    public static Set<Integer> getKeysSet() {
        return keysSet;
    }

    public static boolean isKeyPressed(String key) {

        String[] keys = key.split(":");
        int all = 0;
        if (keys[keys.length - 1].endsWith(";")) {
            keys[keys.length - 1] = keys[keys.length - 1].substring(0, keys[keys.length - 1].length() - 1);
        }

        for (String s : keys) {
            String keyToCompare = s.toUpperCase();
            for (Integer i : keysSet) {
                String string = KeyEvent.getKeyText(i).toUpperCase();
                try {
                    if (string.hashCode() == keyToCompare.hashCode()) {
                        if (string.equals(keyToCompare)) {
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

    public static boolean isKeyPressed(Integer keyText) {

        for (Integer i : keysSet) {
            try {
                if (i.hashCode() == keyText.hashCode()) {
                    if (i.equals(keyText)) {
                        return true;
                    }
                }
            } catch (Exception ignore) {
            }
        }

        return false;
    }

    /**
     * @param keysSet the keysSet to set
     */
    public static void setKeysSet(Set<Integer> keysSet) {
        YldInput.keysSet = keysSet;
    }

}
