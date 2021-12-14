package yield.core;

import yield.YldGame;
import yield.display.YldDrawTarget;
import yield.objects.YldB;
import yield.display.YldGraphical;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

public class YldGameDT implements YldDrawTarget {

    @Override
    public void draw(Graphics g) {
        try {
            HashSet<YldGraphical> yldGraphics = new HashSet<>();
            YldGame.getHandler().getbHashSet().forEach(b -> {
                if (b instanceof YldGraphical)
                    yldGraphics.add((YldGraphical) b);
            });
            YldGame.drawB(yldGraphics.toArray(new YldB[0]), g);
        } catch (ConcurrentModificationException e) {
            draw(g);
        }
    }

}
