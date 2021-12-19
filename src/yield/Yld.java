package yield;

import yield.display.YldFrame;

public final class Yld {

    public static final String YIELD_VERSION = "3.8 Public Beta 9";

    private static YldGame mainGame;

    private static YldFrame mainFrame;

    private static int windowWidth, windowHeight, fps, renderFps, width, height;

    public static YldGame getMainGame() {
        return mainGame;
    }

    public static void setMainGame(YldGame mainGame) {
        Yld.mainGame = mainGame;
    }

    public static YldFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(YldFrame mainFrame) {
        Yld.mainFrame = mainFrame;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static void setWindowWidth(int windowWidth) {
        Yld.windowWidth = windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(int windowHeight) {
        Yld.windowHeight = windowHeight;
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        YldGame.getHandler().getLoop().setFps(fps);
    }

    public static int getRenderFps() {
        return renderFps;
    }

    public static void setRenderFps(int fps) {
        mainGame.getGraphicsLoop().setFps(fps);
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Yld.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Yld.height = height;
    }
}
