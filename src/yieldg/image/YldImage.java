package yieldg.image;

import java.awt.Graphics2D;

import yield.YldGScript;
import yieldg.YldSprite;
import yieldg.util.HotPoint;

public class YldImage extends YldGScript {

    private HotPoint hotPoint = HotPoint.NONE;
    private int x, y;
    private YldSprite sprite;

    @Override
    public String tag() {
        return "YldImage";
    }

    public YldImage(YldSprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(Graphics2D g) {
        sprite.draw(x, y, hotPoint, g);
    }

    public HotPoint getHotPoint() {
        return hotPoint;
    }

    public void setHotPoint(HotPoint hotPoint) {
        this.hotPoint = hotPoint;
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

    public YldSprite getSprite() {
        return sprite;
    }

    public void setSprite(YldSprite sprite) {
        this.sprite = sprite;
    }


    
}
