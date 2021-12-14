package yield.core.loop;

public interface YldLoopable {

    public void tick();

    public default void tickStart() {
    }

    public default void tickEnd() {
    }

}

