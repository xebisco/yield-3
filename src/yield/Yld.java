package yield;

import java.util.HashSet;

import yield.util.YldAction;
import yield.util.YldCoroutine;

public interface Yld {

    public default void callCoroutine(YldAction action) {
        HashSet<YldAction> set = new HashSet<>();
        set.add(action);
        callCoroutine(set);
    }

    public default void callCoroutine(HashSet<YldAction> actions) {
        YldCoroutine coroutine = new YldCoroutine();
        coroutine.setActions(actions);
        coroutine.start();
    }

    public default void print(Object x) {
        System.out.println(x);
    }

}
