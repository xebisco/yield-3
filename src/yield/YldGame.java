package yield;

import yield.core.YldBHandler;
import yield.core.YldGameDT;
import yield.core.loop.YldLoop;
import yield.core.loop.YldLoopable;
import yield.display.YldDrawTarget;
import yield.exceptions.YldSceneException;
import yield.objects.YldB;
import yield.display.YldGraphical;
import yield.objects.YldLayable;
import yield.objects.YldObject;
import yield.objects.YldScene;
import yield.util.YldInput;
import yield.util.YldMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public final class YldGame extends JPanel implements YldLoopable {

    private static YldBHandler handler;

    private static HashSet<YldScene> scenes;

    private final YldLoop graphicsLoop = new YldLoop(this);

    private static final HashSet<YldObject> OBJECTS = new HashSet<>();

    private static BufferedImage image;

    private int imageType = BufferedImage.TYPE_INT_ARGB;

    private final List<YldDrawTarget> drawTargets = new ArrayList<>();

    private static boolean antialiasing = false;

    public YldGame(int width, int height, YldScene startScene) {
        Locale.setDefault(Locale.US);
        Yld.setMainGame(this);
        image = new BufferedImage(width, height, imageType);
        handler = new YldBHandler();
        handler.setExtendLoop(this);
        scenes = new HashSet<>();
        addScene(startScene);
        switchScene(startScene.getSceneName());
        repaint();
        drawTargets.add(new YldGameDT());
        graphicsLoop.startThread();
        new YldTime();
        YldMouse mouse = new YldMouse();
        Yld.getMainFrame().addMouseListener(mouse);
        Yld.getMainFrame().addMouseWheelListener(mouse);
        Yld.getMainFrame().addKeyListener(new YldInput());
    }

    @Override
    public void tick() {
        repaint();
        Yld.setFps((int) getHandler().getLoop().getFps());
        Yld.setRenderFps((int)graphicsLoop.getFps());
    }

    public static void addScene(YldScene scene) {
        try {
            scenes.forEach(s -> {
                if (s.getSceneName().hashCode() == scene.getSceneName().hashCode())
                    if (s.getSceneName().equals(scene.getSceneName()))
                        throw new YldSceneException("A scene already have the name: " + scene.getSceneName());
            });
            scenes.add(scene);
        } catch (ConcurrentModificationException e) {
            addScene(scene);
        }
    }

    public static void switchScene(String sceneName) {
        try {
            handler.getbHashSet().forEach(b -> {
                if (b instanceof YldScene) {
                    handler.getbHashSet().remove(b);
                }
            });
            scenes.forEach(scene -> {
                if (scene.getSceneName().hashCode() == sceneName.hashCode())
                    if (scene.getSceneName().equals(sceneName)) {
                        handler.getbHashSet().add(scene);
                        scene.enter();
                        scene.getObjects().forEach(YldObject::enter);
                    }
            });

        } catch (ConcurrentModificationException e) {
            switchScene(sceneName);
        }
    }

    public static void drawB(YldLayable[] objects, Graphics g) {
        try {
            HashSet<YldGraphical> graphics = new HashSet<>();

            for (YldLayable b : objects) {
                if (b instanceof YldGraphical)
                    graphics.add((YldGraphical) b);
            }

            int layer = 0, maxLayer = 0;
            while (layer <= maxLayer) {
                for (YldGraphical b : graphics) {
                    YldLayable bt = (YldLayable) b;
                    if (maxLayer < bt.getLayer())
                        maxLayer = bt.getLayer();
                    if (layer == bt.getLayer() && bt.isActive())
                        b.draw(g);
                }
                layer++;
            }
        } catch (ConcurrentModificationException e) {
            drawB(objects, g);
        }

    }

    @Override
    public void update(Graphics g) {
        paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Yld.setWidth(getWidth());
        Yld.setHeight(getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawTargets.forEach(dt -> dt.drawExclusive(g));
        Graphics ig = image.getGraphics();
        ig.setColor(new Color(0, 0, 0));
        ig.fillRect(0, 0, image.getWidth(), image.getHeight());
        Graphics2D g2 = (Graphics2D) ig;
        if (antialiasing)
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTargets.forEach(dt -> dt.draw(ig));
        ig.dispose();
        g2.dispose();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.dispose();
    }

    public static YldBHandler getHandler() {
        return handler;
    }

    public static void setHandler(YldBHandler handler) {
        YldGame.handler = handler;
    }

    public static HashSet<YldScene> getScenes() {
        return scenes;
    }

    public static void setScenes(HashSet<YldScene> scenes) {
        YldGame.scenes = scenes;
    }

    public static HashSet<YldObject> getObjects() {
        return OBJECTS;
    }

    public List<YldDrawTarget> getDrawTargets() {
        return drawTargets;
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static void setImage(BufferedImage image) {
        YldGame.image = image;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public static boolean isAntialiasing() {
        return antialiasing;
    }

    public static void setAntialiasing(boolean antialiasing) {
        YldGame.antialiasing = antialiasing;
    }

    public YldLoop getGraphicsLoop() {
        return graphicsLoop;
    }
}
