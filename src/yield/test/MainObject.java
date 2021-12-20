package yield.test;

import yield.components.YldImage;
import yield.display.Vector;
import yield.objects.YldObject;
import yield.util.YldInput;

import java.awt.event.KeyEvent;

class MainObject extends YldObject {

    @Override
    public void create() {
        super.create();
        add(new YldImage());
    }

    @Override
    public void update() {
        super.update();
        if (YldInput.isKeyPressed(KeyEvent.VK_RIGHT)) {
            axis.position.translate(new Vector(10, 0));
        }
    }
}
