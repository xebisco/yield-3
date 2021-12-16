package yield.util;

import yield.core.ThreadType;

public final class YldCoroutine extends ThreadType {

    private final YldAction perform;

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
