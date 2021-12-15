package yieldg.ui.canvas;

import java.awt.*;

public abstract class YldCanvasScript extends YldGScript {

    private int canvasLayer = -1;
    private boolean draw = true;
    protected YldCanvas canvas;

    public YldCanvasScript() {
    }

    public YldCanvasScript(YldCanvas canvas) {
        canvas.addScript(this);
    }

    public void draw(Graphics2D g) {

    }

    public void updateDraw(Graphics2D g) {
        if (getFrames() > 1) {
            draw(g);
        }
    }

    public int getCanvasLayer() {
        return canvasLayer;
    }

    public void setCanvasLayer(int canvasLayer) {
        this.canvasLayer = canvasLayer;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public YldCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(YldCanvas canvas) {
        this.canvas = canvas;
    }

    
}
