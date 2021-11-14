package yieldg.ui;

import yield.util.input.YldMouse;
import yieldg.ui.canvas.YldCanvas;
import yieldg.ui.canvas.YldCanvasScript;

import java.awt.*;

public class YldSlider extends YldCanvasScript {

    @Override
    public String tag() {
        return "YldSlider";
    }

    public YldSlider(YldCanvas canvas) {
        super(canvas);
    }

    public YldSlider() {
    }

    private double x = 200, y = 200, width = 200, height = 5, clickerX = width / 1.3, clickerY, clickerWidth = 20, clickerOffset = 70, clickerHeight = 20, value;
    private YldPalette palette;
    private boolean pressed;

    @Override
    public void tick() {
        clickerY = y - clickerHeight / 2;
        if (YldMouse.isPressing()) {
            if (YldMouse.isTouching(x - clickerOffset / 2, y - clickerOffset / 2, width + clickerOffset, height + clickerOffset)) {
                if(isDraw())
                clickerX = YldMouse.getMouseX() - clickerWidth / 2 - x;
                pressed = true;
            }
        } else {
            pressed = false;
        }
        if (clickerX + clickerWidth / 2 < 0) {
            clickerX = -clickerWidth / 2;
        }
        if (clickerX + clickerWidth / 2 > width) {
            clickerX = width - clickerWidth / 2;
        }

        value = ((clickerX + 10) / (width));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(palette.auxColor4);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(palette.auxColor2);
        g.fillRect((int) x, (int) y, (int) (clickerX + x + clickerWidth / 2 - x), (int) height);
        g.setColor(palette.auxColor1);
        g.fillOval((int) (clickerX + x), (int) clickerY, (int) clickerWidth, (int) clickerHeight);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getClickerX() {
        return clickerX;
    }

    public void setClickerX(double clickerX) {
        this.clickerX = clickerX;
    }

    public double getClickerY() {
        return clickerY;
    }

    public void setClickerY(double clickerY) {
        this.clickerY = clickerY;
    }

    public double getClickerWidth() {
        return clickerWidth;
    }

    public void setClickerWidth(double clickerWidth) {
        this.clickerWidth = clickerWidth;
    }

    public double getClickerHeight() {
        return clickerHeight;
    }

    public void setClickerHeight(double clickerHeight) {
        this.clickerHeight = clickerHeight;
    }

    public YldPalette getPalette() {
        return palette;
    }

    public void setPalette(YldPalette palette) {
        this.palette = palette;
    }

    public double getClickerOffset() {
        return clickerOffset;
    }

    public void setClickerOffset(double clickerOffset) {
        this.clickerOffset = clickerOffset;
    }

    /**
     * @return the value between 0.0 and 1.0
     */
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
