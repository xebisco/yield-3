package yield.core;

import java.util.ConcurrentModificationException;
import java.util.HashSet;

import yield.objects.YldB;
import yield.core.loop.YldLoop;
import yield.core.loop.YldLoopable;

public class YldBHandler implements YldLoopable {

    private final HashSet<YldB> bHashSet = new HashSet<>();
    private YldLoopable extendLoop;
    private final YldLoop loop = new YldLoop(this);

    public YldBHandler() {
        loop.startThread();
    }

    @Override
    public void tick() {
        try {
            updateB(bHashSet.toArray(new YldB[0]));
            if (extendLoop != null)
                extendLoop.tick();
        } catch (ConcurrentModificationException e) {
            tick();
        }
    }

    @Override
    public void tickStart() {
        if (extendLoop != null)
            extendLoop.tickStart();
    }

    @Override
    public void tickEnd() {
        if (extendLoop != null)
            extendLoop.tickEnd();
    }

    public static void updateB(YldB[] objects) {
        try {
            for (int i = 0; i < objects.length; i++) {
                YldB b = objects[i];
                if (b.getLayer() < 0)
                    b.setLayer(0);
                b.setFrames(b.getFrames() + 1);
                if (b.getFrames() == 1)
                    b.create();
            }
            int layer = 0, maxLayer = 0;
            while (layer <= maxLayer) {
                for (int i = 0; i < objects.length; i++) {
                    YldB b = objects[i];
                    if (maxLayer < b.getLayer())
                        maxLayer = b.getLayer();
                    if (layer == b.getLayer() && b.isActive() && b.getFrames() > 1)
                        b.update();
                }
                layer++;
            }
        } catch (ConcurrentModificationException e) {
            updateB(objects);
        }

    }

    public HashSet<YldB> getbHashSet() {
        return bHashSet;
    }

    public YldLoop getLoop() {
        return loop;
    }

    public YldLoopable getExtendLoop() {
        return extendLoop;
    }

    public void setExtendLoop(YldLoopable extendLoop) {
        this.extendLoop = extendLoop;
    }
}
