package yield;

public class Yld {

    public static final String YIELD_VERSION = "3.8 Public Beta 3";

    private static YldGame mainGame;

    public static int width, height;

    public static YldGame getMainGame() {
        return mainGame;
    }

    public static void setMainGame(YldGame mainGame) {
        Yld.mainGame = mainGame;
    }



}
