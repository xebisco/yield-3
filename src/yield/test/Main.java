package yield.test;

import yield.YldGame;
import yield.display.YldFrame;

class Main extends YldFrame {

    /*public static void main(String[] args) {
        new Main();
    }*/

    private Main() {
        super();
        add(new YldGame(1280, 720, new MainScene()));
        YldGame.setAntialiasing(true);
        setVisible(true);
    }

}
