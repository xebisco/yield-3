package yield.display;

import java.awt.*;

public interface YldDrawTarget {

    public void draw(Graphics g);

    public default void drawExclusive(Graphics g) {

    }

}
