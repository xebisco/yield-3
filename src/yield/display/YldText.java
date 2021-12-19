package yield.display;

import yield.components.YldString;
import yield.objects.YldObject;

import javax.swing.*;
import java.awt.*;

public class YldText extends YldObject {

    private int x, y;
    private final String text;

    public YldText(){
        this.text = null;
    }

    public YldText(String text) {
        this.text = text;
    }

    @Override
    public void create() {
        super.create();
        add(new YldString(text));
    }

    public void setX(float x) {
        axis.position.setX(x);
    }

    public void setY(float y) {
        axis.position.setY(y);
    }
}
