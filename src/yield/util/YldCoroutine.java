package yield.util;

import yield.core.ThreadType;

public class YldCoroutine extends ThreadType {

    private YldAction perform;

    public YldCoroutine(YldAction perform) {
        this.perform = perform;
        setInterruptOnEnd(true);
        startThread();
    }

    @Override
    public void start() {
        perform.onAction();
    }

}
