package yield.test;

import yield.YldTime;
import yield.components.YldString;
import yield.display.YldSprite;
import yield.display.YldText;
import yield.objects.YldScene;
import yield.util.YldInput;

import java.awt.event.KeyEvent;

class MainScene extends YldScene {

    @Override
    public void create() {
        super.create();
        add(new MainObject());
    }

    @Override
    public void update() {
        super.update();
    }
}
