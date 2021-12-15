package yield;

public class Yld {

    public static final String YIELD_VERSION = "3.8 Public Beta 4";

    private static YldGame mainGame;

    public static int windowWidth, windowHeight, fps, renderFps;

    public static YldGame getMainGame() {
        return mainGame;
    }

    public static void setMainGame(YldGame mainGame) {
        Yld.mainGame = mainGame;
    }



}
