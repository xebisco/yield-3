package yield.display;

import yield.objects.YldObject;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class YldImage extends YldObject implements YldGraphical {

    private Image image;
    private static Image nullImage = new ImageIcon(Objects.requireNonNull(YldFrame.class.getResource("/yield/Yield Icon.png"))).getImage();

    private int x, y;

    public YldImage(){

    }

    public YldImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        if(image != null)
            g.drawImage(image, x, y, null);
        else
            g.drawImage(nullImage, x, y, null);
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

    public static void setNullImage(Image nullImage) {
        YldImage.nullImage = nullImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
