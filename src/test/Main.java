package test;

import yield.YldGame;
import yield.display.YldFrame;

public class Main extends YldFrame {

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        super();
        add(new YldGame(1280, 720, new MainScene()));
        setVisible(true);
    }

}
