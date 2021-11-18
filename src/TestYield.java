import yield.YldApp;
import mapp.*;

public class TestYield extends YldApp {

    @Override
    public void start() {
        startYieldUI = true;
        startYield();
    }

    public static void main(String[] args) {
        new TestYield();
    }
    
}
