package yield.display;

import yield.Yld;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class YldFrame extends JFrame {

    public YldFrame() {
        start(1280, 720);
    }

    public YldFrame(int width, int height) {
        start(width, height);
    }

    private void start(int width, int height) {
        int iw = 0, ih = 0;
        setSize(100, 100);
        setVisible(true);
        iw = getInsets().left + getInsets().right;
        ih = getInsets().top + getInsets().bottom;
        dispose();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage((new ImageIcon(Objects.requireNonNull(YldFrame.class.getResource("/yield/Yield Icon.png")))).getImage());
        setTitle("Yield Game Engine v." + Yld.YIELD_VERSION);
        setResizable(false);
        setSize(width + iw, height + ih);
        setLocationRelativeTo(null);
    }
}
