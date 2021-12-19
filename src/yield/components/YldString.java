package yield.components;

import yield.display.YldGraphical;

import java.awt.*;
import java.util.Objects;

public class YldString extends YldComponent implements YldGraphical {

    private String text;
    private Color color = Color.white;
    private Font font = new Font("arial", Font.BOLD, 70);
    private final static String nullText = "null";

    public YldString() {

    }

    public YldString(String text) {
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(Objects.requireNonNullElse(text, nullText), (int) object.getAxis().position.getX(), (int) object.getAxis().position.getY() + g.getFont().getSize());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public static String getNullText() {
        return nullText;
    }
}
