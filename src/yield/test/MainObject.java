package yield.test;

import yield.components.YldImage;
import yield.objects.YldObject;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

class MainObject extends YldObject {

    @Override
    public void create() {
        super.create();
        add(new YldImage(new ImageIcon(Objects.requireNonNull(MainObject.class.getResource("/yield/YieldP.png"))).getImage()));
        axis.scale.setImageScale(((YldImage) getComponent("YldImage")).getImage());
    }

    @Override
    public void update() {
        super.update();
    }
}
