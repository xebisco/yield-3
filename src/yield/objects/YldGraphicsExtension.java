package yield.objects;

import yield.YldGame;
import yield.components.YldComponent;
import yield.display.YldGraphical;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

public class YldGraphicsExtension extends YldObject implements YldGraphical {

    private HashSet<YldComponent> graphicsComponents;

    @Override
    public void draw(Graphics g) {
        try {
            HashSet<YldGraphical> yldGraphics = new HashSet<>();
            if (graphicsComponents != null)
                graphicsComponents.forEach(b -> {
                    if (b instanceof YldGraphical)
                        yldGraphics.add((YldGraphical) b);
                });
            YldGame.drawB(yldGraphics.toArray(new YldLayable[0]), g);
        } catch (
                ConcurrentModificationException e) {
            draw(g);
        }
    }

    public HashSet<YldComponent> getGraphicsComponents() {
        return graphicsComponents;
    }

    public void setGraphicsComponents(HashSet<YldComponent> graphicsComponents) {
        this.graphicsComponents = graphicsComponents;
    }
}
