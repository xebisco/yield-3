package yield.components;

import yield.objects.YldLayable;
import yield.objects.YldMethods;
import yield.objects.YldObject;

public class YldComponent extends YldLayable implements YldMethods {

    protected YldObject object;
    private final String componentName = getClass().getSimpleName();

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    public YldObject getObject() {
        return object;
    }

    public void setObject(YldObject object) {
        this.object = object;
    }

    public String getComponentName() {
        return componentName;
    }
}
