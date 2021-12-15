package yieldg.ui.canvas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yieldg.ui.canvas.input.YldCanvasMouse;

public class YldCanvas {

    private BufferedImage canvasImage;
    private int imageType = BufferedImage.TYPE_INT_ARGB, width = 1280, height = 720;
    private YldBody canvasBody;
    private Graphics2D imageGraphics;
    private YldCanvasMouse mouse;
    private List<YldCanvasScript> scripts;
    private int canvasLayer = 500;

    public YldCanvas() {
        start();
    }

    public YldCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        start();
    }

    private void start() {
        scripts = new ArrayList<>();
        if (mouse == null) {
            mouse = new YldCanvasMouse();
            mouse.setResolution(new Dimension(width, height));
            YldApp.yld.getYldGraphicsEngine().addMouseListener(mouse);
            YldApp.yld.getYldGraphicsEngine().addMouseWheelListener(mouse);
        }
        canvasBody = new YldBody() {
            @Override
            public void awake() {
                addScript(new YldGScript() {
                    @Override
                    public String tag() {
                        return "CanvasScript";
                    }

                    @Override
                    public void tick() {
                        for (int i = 0; i < scripts.size(); i++) {
                            YldCanvasScript script = scripts.get(i);
                            if (script.getCanvasLayer() >= 0) {
                                if (script.getCanvasLayer() < scripts.size()) {
                                    Collections.swap(scripts, script.getCanvasLayer(), i);
                                } else {
                                    Collections.swap(scripts, scripts.size() - 1, i);
                                }
                                script.setCanvasLayer(-1);
                            }
                        }
                    }

                    @Override
                    public void render(Graphics2D g) {
                        setLayer(canvasLayer);
                        canvasImage = new BufferedImage(width, height, imageType);
                        imageGraphics = canvasImage.createGraphics();
                        for (int i = 0; i < scripts.size(); i++) {
                            YldCanvasScript script = scripts.get(i);
                            try {
                                if (script.isDraw())
                                    script.draw(imageGraphics);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        imageGraphics.dispose();

                        g.drawImage(canvasImage, 0, 0, yld.width, yld.height, null);

                        canvasImage.flush();
                    }
                });
            }
        };
    }

    public void addScript(YldCanvasScript canvasScript) {
        scripts.add(canvasScript);
        canvasScript.setCanvas(this);
    }

    public BufferedImage getCanvasImage() {
        return canvasImage;
    }

    public void setCanvasImage(BufferedImage canvasImage) {
        this.canvasImage = canvasImage;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public YldBody getCanvasBody() {
        return canvasBody;
    }

    public void setCanvasBody(YldBody canvasBody) {
        this.canvasBody = canvasBody;
    }

    public Graphics2D getImageGraphics() {
        return imageGraphics;
    }

    public void setImageGraphics(Graphics2D imageGraphics) {
        this.imageGraphics = imageGraphics;
    }

    public List<YldCanvasScript> getScripts() {
        return scripts;
    }

    public void setScripts(List<YldCanvasScript> scripts) {
        this.scripts = scripts;
    }

    public int getCanvasLayer() {
        return canvasLayer;
    }

    public void setCanvasLayer(int canvasLayer) {
        this.canvasLayer = canvasLayer;
    }

    public YldCanvasMouse getMouse() {
        return mouse;
    }

    public void setMouse(YldCanvasMouse mouse) {
        this.mouse = mouse;
    }

}
