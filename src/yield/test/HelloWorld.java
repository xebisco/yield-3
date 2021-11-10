package yield.test;

import yield.YldApp;
import yieldg.text.YldText;
import yieldg.util.HotPoint;

/**
 * Hello World app for Yield
 */
public class HelloWorld extends YldApp {

    @Override
    public void start() {
        startYield();
        new YldText("Hello, World!", yld.width / 2, yld.height / 2).setHotPoint(HotPoint.MIDDLE);
        System.out.println("Hello, World!");
    }

    public static void run() {
        new HelloWorld();
    }

    public HelloWorld() {
        run();
    }

}
