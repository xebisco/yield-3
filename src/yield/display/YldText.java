package yield.display;

import yield.objects.YldObject;

import javax.swing.*;
import java.awt.*;

public class YldText extends YldObject implements YldGraphical {

    private String text;
    private Color color = Color.white;
    private Font font = new Font("arial", Font.BOLD, 70);
    private static String nullText = "null";

    private int x, y;

    public YldText(){

    }

    public YldText(String text) {
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        if(text != null)
            g.drawString(text, x, y + g.getFont().getSize());
        else
            g.drawString(nullText, x, y + g.getFont().getSize());
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

    public static void setNullText(String nullText) {
        YldText.nullText = nullText;
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
