package yield.objects;

import yield.YldGame;

public abstract class YldB {

    private int layer = 0, frames;

    private boolean active = true;

    public abstract void create();

    public abstract void update();

    public void load() {
        YldGame.getHandler().getbHashSet().add(this);
    }

    public void remove() {
        YldGame.getHandler().getbHashSet().remove(this);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

