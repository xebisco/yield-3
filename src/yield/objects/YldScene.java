package yield.objects;

import yield.YldGame;
import yield.components.YldComponent;
import yield.core.YldBHandler;
import yield.display.YldGraphical;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

public abstract class YldScene extends YldB implements YldGraphical {

    private String sceneName = getClass().getSimpleName();
    private HashSet<YldObject> objects = new HashSet<>();

    @Override
    public void create() {

    }

    /**
     * This method is called when YldGame switch to this scene
     */
    public void enter() {

    }

    @Override
    public void update() {
        YldBHandler.updateB(objects.toArray(new YldB[0]));
    }

    @Override
    public void draw(Graphics g) {
        try {
            HashSet<YldGraphical> yldGraphics = new HashSet<>();
            objects.forEach(b -> {
                if (b instanceof YldGraphical)
                    yldGraphics.add((YldGraphical) b);
            });
            YldGame.drawB(yldGraphics.toArray(new YldLayable[0]), g);
        } catch (ConcurrentModificationException e) {
            draw(g);
        }
    }

    /**
     * This method is not used in YldScene, use YldGame.addScene() instead!
     */
    @Override
    public void load() {

    }

    public YldObject[] getObjects(String name) {
        final HashSet<YldObject> objects0 = new HashSet<>();

        objects.forEach(c -> {
            if (c.getName().hashCode() == name.hashCode())
                if (c.getName().equals(name))
                    objects0.add(c);
        });

        return objects0.toArray(new YldObject[0]);
    }

    public YldObject getObject(String name) {
        return getObjects(name)[0];
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public YldObject add(YldObject object) {
        objects.add(object);
        object.setScene(this);
        return object;
    }

    public void remove(YldObject object) {
        objects.remove(object);
    }

    public YldObject removeObject(YldObject object) {
        objects.remove(object);
        return object;
    }

    public HashSet<YldObject> getObjects() {
        return objects;
    }

    public void setObjects(HashSet<YldObject> objects) {
        this.objects = objects;
    }
}
