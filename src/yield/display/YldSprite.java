package yield.display;

import yield.components.YldImage;
import yield.objects.YldObject;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class YldSprite extends YldObject {

    private Image image;
    private static final Image nullImage = new ImageIcon(Objects.requireNonNull(YldFrame.class.getResource("/yield/Yield Icon.png"))).getImage();

    public YldSprite() {

    }

    public YldSprite(Image image) {
        this.image = image;
    }

    @Override
    public void create() {
        super.create();
        add(new YldImage(image));
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static Image getNullImage() {
        return nullImage;
    }

    public void setX(float x) {
        axis.position.setX(x);
    }

    public void setY(float y) {
        axis.position.setY(y);
    }

}
