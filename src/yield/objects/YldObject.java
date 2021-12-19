package yield.objects;

import yield.YldGame;
import yield.components.YldAxis;
import yield.components.YldComponent;

import java.util.HashSet;

public abstract class YldObject extends YldB {

    private final String name = getClass().getSimpleName();
    private YldScene scene;
    private final HashSet<YldComponent> components = new HashSet<>();
    protected YldAxis axis;
    private YldGraphicsExtension graphicsExtension;
    private boolean startGraphicsExtension = true;

    @Override
    public void create() {
        axis = new YldAxis();
        components.add(axis);
        if (startGraphicsExtension)
            startGraphicsExtension();
    }

    /**
     * This method is called when this YldObject scene enter.
     */
    public void enter() {

    }

    @Override
    public void update() {

    }

    public YldComponent[] getComponents(String componentName) {
        final HashSet<YldComponent> components0 = new HashSet<>();

        components.forEach(c -> {
            if (c.getComponentName().hashCode() == componentName.hashCode())
                if (c.getComponentName().equals(componentName))
                    components0.add(c);
        });

        return components0.toArray(new YldComponent[0]);
    }

    public YldComponent getComponent(String componentName) {
        return getComponents(componentName)[0];
    }

    private static YldObject searchMethodObject;

    public final YldObject search(String name) {
        searchMethodObject = null;
        YldGame.getObjects().forEach(b -> {
            if (b.getName().hashCode() == name.hashCode())
                if (b.getName().equals(name))
                    searchMethodObject = b;
        });
        return searchMethodObject;
    }

    @Override
    public void load() {
        YldGame.getObjects().add(this);
    }

    @Override
    public void remove() {
        YldGame.getObjects().remove(this);
    }

    public String getName() {
        return name;
    }

    public HashSet<YldComponent> getComponents() {
        return components;
    }

    public YldAxis getAxis() {
        return axis;
    }

    public void setAxis(YldAxis axis) {
        this.axis = axis;
    }

    public void add(YldComponent component) {
        component.setObject(this);
        components.add(component);
    }

    public YldScene getScene() {
        return scene;
    }

    public void setScene(YldScene scene) {
        this.scene = scene;
    }

    public YldGraphicsExtension getGraphicsExtension() {
        return graphicsExtension;
    }

    public void setGraphicsExtension(YldGraphicsExtension graphicsExtension) {
        this.graphicsExtension = graphicsExtension;
    }

    public void startGraphicsExtension() {
        graphicsExtension = new YldGraphicsExtension();
        graphicsExtension.setGraphicsComponents(components);
        scene.add(graphicsExtension);
    }

    public boolean isStartGraphicsExtension() {
        return startGraphicsExtension;
    }

    public void setStartGraphicsExtension(boolean startGraphicsExtension) {
        this.startGraphicsExtension = startGraphicsExtension;
    }
}
