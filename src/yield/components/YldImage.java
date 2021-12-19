package yield.components;

import yield.display.YldFrame;
import yield.display.YldGraphical;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class YldImage extends YldComponent implements YldGraphical {

    private Image image;
    private static final Image nullImage = new ImageIcon(Objects.requireNonNull(YldFrame.class.getResource("/yield/Yield Icon.png"))).getImage();

    public YldImage() {

    }

    public YldImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        if (image != null)
            g.drawImage(image, (int) getObject().getAxis().position.getX(), (int) getObject().getAxis().position.getY(), (int) getObject().getAxis().scale.getX(), (int) getObject().getAxis().scale.getY(), null);
        else
            g.drawImage(nullImage, (int) getObject().getAxis().position.getX(), (int) getObject().getAxis().position.getY(), (int) getObject().getAxis().scale.getX(), (int) getObject().getAxis().scale.getY(), null);
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

}
