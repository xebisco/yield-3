package yield.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashSet;

import yield.YldApp;
import yield.YldGScript;
import yield.YldRoom;
import yield.YldScript;
import yield.core.engines.YldGraphicsEngine;
import yield.core.engines.YldLogicEngine;
import yield.core.engines.interfaces.YldGraphical;
import yield.core.engines.interfaces.YldLogical;
import yield.util.YldReader;
import yield.util.YldWriter;
import yieldg.YldSprite;

/**
 * O YldCore é o coração do jogo, ele que manda para todos os objetos o que as
 * engines fazem.
 */
public class YldCore implements YldLogical, YldGraphical {

    public static boolean yieldpresentation;
    public YldApp app;

    private HashSet<YldScript> yldScripts;
    private YldLogicEngine yldLogicEngine;
    private YldGraphicsEngine yldGraphicsEngine;
    public int TPS, FPS, width, height;
    private YldSprite yieldlogo = new YldSprite("/YieldP.png");
    private Graphics2D mainGraphics, nonImageGraphics;
    private boolean showEmptyWarning = true;

    public YldCore() {
        yldScripts = new HashSet<>();
        YldWriter.writer = new YldWriter();
        YldReader.reader = new YldReader();
    }

    public void startEngines() {
        yldLogicEngine = new YldLogicEngine(this);
        yldGraphicsEngine = new YldGraphicsEngine(this);
        yldLogicEngine.setGraphicsEngine(yldGraphicsEngine);
    }

    @Override
    public void update() {
        TPS = (int) yldLogicEngine.getTPS();

        if (yldScripts != null) {

            int n = -1, maxLayer = 1;

            try {
                while (n <= maxLayer) {
                    for (YldScript script : yldScripts) {
                        if (maxLayer < script.getLayer())
                            maxLayer = script.getLayer();
                        boolean call = false;
                        if (n == script.getLayer()) {
                            try {
                                if (script.getBody().getRoom() == YldRoom.getActRoom())
                                    call = true;
                                else
                                    call = false;
                                if (script.getBody() == null || script.getBody().getRoom() == null)
                                    call = true;
                            } catch (Exception e) {
                                call = true;
                            }
                        }
                        if (call)
                            script.update();
                    }
                    n++;
                }

            } catch (Exception ignore) {

            }

            /*
             * for (int i = 0; i < yldScripts.size(); i++) { YldScript script =
             * yldScripts.get(i); if (script.getLayer() >= 0) { if (script.getLayer() <
             * yldScripts.size()) { Collections.swap(yldScripts, script.getLayer(), i); }
             * else { Collections.swap(yldScripts, yldScripts.size() - 1, i); }
             * script.setLayer(-1); } if (script.getBody() != null) { if
             * (script.getBody().getRoom() != null) { if (script.getBody().getRoom() ==
             * YldRoom.getActRoom()) { script.update(); } } else { script.update(); } } else
             * { script.update(); } }
             */

        }
    }

    @Override
    public void render(Graphics2D g) {
        TPS = (int) yldGraphicsEngine.getFPS();
        width = yldGraphicsEngine.getWWidth();
        height = yldGraphicsEngine.getWHeight();
        int n1 = 0;
        int n = -1, maxLayer = 1;

        try {
            while (n <= maxLayer) {
                for (YldScript script : yldScripts) {
                    AffineTransform at = g.getTransform();
                    if (maxLayer < script.getLayer())
                        maxLayer = script.getLayer();
                    boolean call = false;
                    if (n == script.getLayer()) {
                        try {
                            if (script.getBody().getRoom() == YldRoom.getActRoom())
                                call = true;
                            else
                                call = false;
                                if (script.getBody() == null || script.getBody().getRoom() == null)
                                call = true;
                        } catch (Exception e) {
                            call = true;
                        }
                    }

                    if (call && script instanceof YldGScript) {
                        n1++;
                        ((YldGScript) script).updateRender(g);
                    }
                    g.transform(at);
                }
                n++;
            }

        } catch (Exception ignore) {
        }

        if ((n1 <= 1 && YldRoom.getActRoom() == null) || yieldpresentation) {
            g.clearRect(0, 0, yldGraphicsEngine.getWWidth(), yldGraphicsEngine.getWHeight());
            g.drawImage(yieldlogo.getBufferedImage(),
                    (int) (yldGraphicsEngine.getWWidth() / 2
                            - (int) ((double) yldGraphicsEngine.getWWidth() / 1.706666666666667) / 1.9),
                    (int) (yldGraphicsEngine.getWHeight() / 2
                            - (int) ((double) yldGraphicsEngine.getWHeight() / 1.706666666666667) / 2.6),
                    (int) ((double) yldGraphicsEngine.getWWidth() / 1.706666666666667),
                    (int) ((double) yldGraphicsEngine.getWHeight() / 2.4), null);
            g.setFont(new Font("arial", 0, 10));
            g.setColor(Color.white);
            String string = "version: " + YldApp.YIELD_VERSION + ", made by vtogames";
            g.drawString(string, getYldGraphicsEngine().getWWidth() - g.getFontMetrics().stringWidth(string),
                    getYldGraphicsEngine().getWHeight() - g.getFont().getSize() / 2);
        } else if (n1 <= 1 && showEmptyWarning) {
            String string = "empty!";
            g.setColor(Color.red);
            g.drawString(string, getYldGraphicsEngine().getWWidth() - g.getFontMetrics().stringWidth(string),
                    getYldGraphicsEngine().getWHeight() - g.getFont().getSize() / 2);
        }
    }

    /**
     * @return the yldScripts
     */
    public HashSet<YldScript> getYldScripts() {
        return yldScripts;
    }

    /**
     * @param yldScripts the yldScripts to set
     */
    public void setYldScripts(HashSet<YldScript> yldScripts) {
        this.yldScripts = yldScripts;
    }

    /**
     * @param FPS the FPS to set
     */
    public boolean changeFPS(double FPS) {
        boolean success = true;
        try {
            yldGraphicsEngine.setTargetFPS(FPS);
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    /**
     * @param TPS the TPS to set
     */
    public boolean changeTPS(double TPS) {
        boolean success = true;
        try {
            yldLogicEngine.setTargetTPS(TPS);
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    public boolean loadYldScript(YldScript yldScript) {
        boolean success = true;
        try {
            yldScripts.add(yldScript);
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    /**
     * @return the yldLogicEngine
     */
    public YldLogicEngine getYldLogicEngine() {
        return yldLogicEngine;
    }

    /**
     * @param yldLogicEngine the yldLogicEngine to set
     */
    public void setYldLogicEngine(YldLogicEngine yldLogicEngine) {
        this.yldLogicEngine = yldLogicEngine;
    }

    /**
     * @return the yldGraphicsEngine
     */
    public YldGraphicsEngine getYldGraphicsEngine() {
        return yldGraphicsEngine;
    }

    /**
     * @param yldGraphicsEngine the yldGraphicsEngine to set
     */
    public void setYldGraphicsEngine(YldGraphicsEngine yldGraphicsEngine) {
        this.yldGraphicsEngine = yldGraphicsEngine;
    }

    /**
     * @return the yieldlogo
     */
    public YldSprite getYieldlogo() {
        return yieldlogo;
    }

    public static boolean isYieldpresentation() {
        return yieldpresentation;
    }

    public static void setYieldpresentation(boolean yieldpresentation) {
        YldCore.yieldpresentation = yieldpresentation;
    }

    public YldApp getApp() {
        return app;
    }

    public void setApp(YldApp app) {
        this.app = app;
    }

    public int getTPS() {
        return TPS;
    }

    public void setTPS(int TPS) {
        this.TPS = TPS;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setYieldlogo(YldSprite yieldlogo) {
        this.yieldlogo = yieldlogo;
    }

    public Graphics2D getMainGraphics() {
        return mainGraphics;
    }

    public void setMainGraphics(Graphics2D mainGraphics) {
        this.mainGraphics = mainGraphics;
    }

    public Graphics2D getNonImageGraphics() {
        return nonImageGraphics;
    }

    public void setNonImageGraphics(Graphics2D nonImageGraphics) {
        this.nonImageGraphics = nonImageGraphics;
    }

    public boolean isShowEmptyWarning() {
        return showEmptyWarning;
    }

    public void setShowEmptyWarning(boolean showEmptyWarning) {
        this.showEmptyWarning = showEmptyWarning;
    }

}
