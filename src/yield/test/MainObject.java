package yield.test;

import yield.components.YldAxis;
import yield.components.YldString;
import yield.display.Vector;
import yield.objects.YldObject;

class MainObject extends YldObject {

    @Override
    public void create() {
        super.create();
        add(new YldString("test"));
        axis.position.teleport(new Vector(100, 100));
    }

    @Override
    public void update() {
        super.update();
    }
}
