package yield.objects;

import yield.YldGame;

import java.util.ConcurrentModificationException;

public abstract class YldObject extends YldB {

    private final String name = getClass().getSimpleName();

    @Override
    public void create() {

    }

    /**
     * This method is called when this YldObject scene enter.
     */
    public void enter() {

    }

    @Override
    public void update() {

    }

    private static YldObject searchMethodObject;

    public final YldObject search(String name) {
        searchMethodObject = null;
        YldGame.getObjects().forEach(b -> {
            if (b.getName().hashCode() == name.hashCode())
                if (b.getName().equals(name))
                    searchMethodObject = b;
        });
        return searchMethodObject;
    }

    @Override
    public void load() {
        YldGame.getObjects().add(this);
    }

    @Override
    public void remove() {
        YldGame.getObjects().remove(this);
    }

    public String getName() {
        return name;
    }

}
