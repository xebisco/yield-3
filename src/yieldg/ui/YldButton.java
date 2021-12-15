package yieldg.ui;

import java.awt.Graphics2D;

import yield.util.YldAction;
import yieldg.ui.canvas.YldCanvasScript;

/**
 * NOT FINISHED!
 */
public class YldButton extends YldCanvasScript {

    @Override
    public String tag() {
        return "YldButton";
    }

    private YldPalette palette = YldPalette.getGlobalPallete();

    private boolean selected;
    private int x = 100, y = 100, height, width;
    private YldAction onClickAction;

    @Override
    public void tick() {
        if (canvas.getMouse().isPressing()) {
            if (canvas.getMouse().isTouching(x, y, width, height)) {
                if (onClickAction != null)
                    onClickAction.onAction();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        
    }

    public YldPalette getPalette() {
        return palette;
    }

    public void setPalette(YldPalette palette) {
        this.palette = palette;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public YldAction getOnClickAction() {
        return onClickAction;
    }

    public void setOnClickAction(YldAction onClickAction) {
        this.onClickAction = onClickAction;
    }

    

}
